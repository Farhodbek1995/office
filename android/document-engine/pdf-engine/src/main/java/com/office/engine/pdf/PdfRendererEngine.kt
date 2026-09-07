package com.office.engine.pdf

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import com.office.engine.api.DocumentDestination
import com.office.engine.api.DocumentEngine
import com.office.engine.api.DocumentFormat
import com.office.engine.api.DocumentSession
import com.office.engine.api.DocumentSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * PDF rendering engine built on the platform [PdfRenderer] (reja.txt 13-bo'lim, MVP).
 * Pages are rendered to bitmaps on a background dispatcher; the UI layer owns
 * caching/bitmap-pooling strategy (reja.txt 29-bo'lim).
 */
@Singleton
class PdfRendererEngine @Inject constructor(
    @ApplicationContext private val context: Context
) : DocumentEngine {

    override val supportedFormats: Set<DocumentFormat> = setOf(DocumentFormat.PDF)

    override fun canHandle(format: DocumentFormat): Boolean = format == DocumentFormat.PDF

    override suspend fun open(source: DocumentSource): Result<DocumentSession> =
        withContext(Dispatchers.IO) {
            runCatching {
                val pfd = openDescriptor(source)
                try {
                    val renderer = PdfRenderer(pfd)
                    val sessionId = UUID.randomUUID().toString()
                    val name = when (source) {
                        is DocumentSource.UriSource -> source.uri.lastPathSegment ?: "document.pdf"
                        is DocumentSource.FileSource -> source.file.name
                        is DocumentSource.BytesSource -> source.name
                    }
                    PdfDocumentSession(
                        id = sessionId,
                        name = name,
                        renderer = renderer,
                        descriptor = pfd
                    )
                } catch (t: Throwable) {
                    runCatching { pfd.close() }
                    throw t
                }
            }.fold(
                onSuccess = { Result.success(it) },
                onFailure = { Result.failure(it) }
            )
        }

    override suspend fun export(
        session: DocumentSession,
        format: DocumentFormat,
        destination: DocumentDestination
    ): Result<Unit> = withContext(Dispatchers.IO) {
        // MVP: PDF annotations are rendered as an overlay and exported by the caller;
        // re-encoding a PDF is not required for the first milestone.
        Result.success(Unit)
    }

    private fun openDescriptor(source: DocumentSource): ParcelFileDescriptor = when (source) {
        is DocumentSource.UriSource -> {
            val pfd = context.contentResolver.openFileDescriptor(source.uri, "r")
                ?: throw IllegalStateException("Cannot open URI descriptor")
            pfd
        }
        is DocumentSource.FileSource -> ParcelFileDescriptor.open(
            source.file,
            ParcelFileDescriptor.MODE_READ_ONLY
        )
        is DocumentSource.BytesSource -> {
            // In-memory PDF sources are cached to a temporary file for PdfRenderer.
            val temp = File.createTempFile("office_pdf", ".pdf", context.cacheDir)
            temp.writeBytes(source.bytes)
            ParcelFileDescriptor.open(temp, ParcelFileDescriptor.MODE_READ_ONLY)
        }
    }
}

/**
 * Wraps [PdfRenderer] into a [DocumentSession]. Page rendering is exposed
 * through [renderPage] which the PDF viewer uses for page rendering.
 */
class PdfDocumentSession internal constructor(
    override val id: String,
    override val name: String,
    internal val renderer: PdfRenderer,
    private val descriptor: ParcelFileDescriptor
) : DocumentSession {

    override val format: DocumentFormat = DocumentFormat.PDF

    val pageCount: Int get() = renderer.pageCount

    /** Renders a single page (1-based [pageIndex]) into a [Bitmap]. */
    suspend fun renderPage(pageIndex: Int, targetWidth: Int, targetHeight: Int): Bitmap? =
        withContext(Dispatchers.IO) {
            if (pageIndex !in 1..pageCount) return@withContext null
            val page = renderer.openPage(pageIndex - 1)
            try {
                val scale = minOf(
                    targetWidth.toFloat() / page.width,
                    targetHeight.toFloat() / page.height
                ).coerceAtLeast(0.1f)
                val width = (page.width * scale).toInt().coerceAtLeast(1)
                val height = (page.height * scale).toInt().coerceAtLeast(1)
                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                bitmap.eraseColor(android.graphics.Color.WHITE)
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                bitmap
            } finally {
                page.close()
            }
        }

    override suspend fun save(destination: DocumentDestination?): Result<Unit> =
        Result.success(Unit)

    override suspend fun close() {
        runCatching { renderer.close() }
        runCatching { descriptor.close() }
    }
}
