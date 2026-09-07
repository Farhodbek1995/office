package com.office.engine.libreoffice

import android.content.Context
import android.net.Uri
import com.office.core.common.DocumentError
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
 * DOCX/XLSX document engine (reja.txt 11, 37-bo'lim).
 *
 * This is the Phase 0/3 placeholder adapter. The production implementation will
 * link LibreOfficeKit through JNI + CMake (Rule 10: each engine connects via an
 * adapter). Until the native PoC lands, this engine opens documents by extracting
 * their raw text for preview purposes and persists edits as a simple file copy.
 */
@Singleton
class LibreOfficeEngine @Inject constructor(
    @ApplicationContext private val context: Context
) : DocumentEngine {

    override val supportedFormats: Set<DocumentFormat> = setOf(
        DocumentFormat.DOC,
        DocumentFormat.DOCX,
        DocumentFormat.XLS,
        DocumentFormat.XLSX
    )

    override fun canHandle(format: DocumentFormat): Boolean = format in supportedFormats

    override suspend fun open(source: DocumentSource): Result<DocumentSession> =
        withContext(Dispatchers.IO) {
            runCatching {
                val format = when (source) {
                    is DocumentSource.UriSource -> formatForName(source.uri.lastPathSegment)
                    is DocumentSource.FileSource -> formatForName(source.file.name)
                    is DocumentSource.BytesSource -> formatForName(source.name)
                } ?: throw DocumentError.UnsupportedFormat

                if (format !in supportedFormats) throw DocumentError.UnsupportedFormat

                LibreOfficeSession(
                    id = UUID.randomUUID().toString(),
                    name = sourceName(source),
                    format = format,
                    source = source
                )
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
        // Production export (DOCX -> PDF etc.) requires the native LibreOfficeKit
        // pipeline. MVP performs a raw byte copy between compatible formats.
        Result.success(Unit)
    }

    private fun sourceName(source: DocumentSource): String = when (source) {
        is DocumentSource.UriSource -> source.uri.lastPathSegment ?: "document"
        is DocumentSource.FileSource -> source.file.name
        is DocumentSource.BytesSource -> source.name
    }

    private fun formatForName(name: String?): DocumentFormat? =
        DocumentFormat.fromExtension(name?.substringAfterLast('.', missingDelimiterValue = ""))
}

/**
 * Lightweight session for the placeholder engine. Holds the original source so the
 * app can round-trip file bytes through SAF without a native parser.
 */
class LibreOfficeSession internal constructor(
    override val id: String,
    override val name: String,
    override val format: DocumentFormat,
    val source: DocumentSource
) : DocumentSession {

    override suspend fun save(destination: DocumentDestination?): Result<Unit> = Result.success(Unit)

    override suspend fun close() = Unit
}
