package com.office.core.filesystem

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import com.office.core.common.AppResult
import com.office.core.common.DocumentError
import com.office.core.common.MimeTypes
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Default SAF-backed implementation of [FileSystemManager].
 *
 * All blocking I/O is dispatched onto [Dispatchers.IO] (Rule 5 / reja.txt 30-bo'lim).
 * Creation of new documents is delegated to ACTION_CREATE_DOCUMENT through a pending
 * intent held by the caller; this class returns the URI that the caller must then resolve.
 */
@Singleton
class SafFileSystemManager @Inject constructor(
    @ApplicationContext private val context: Context
) : FileSystemManager {

    private val resolver: ContentResolver get() = context.contentResolver

    override suspend fun open(uri: Uri): AppResult<InputStream> = withContext(Dispatchers.IO) {
        runCatching { resolver.openInputStream(uri) }
            .fold(
                onSuccess = { stream ->
                    if (stream == null) AppResult.failure(DocumentError.FileNotFound)
                    else AppResult.success(stream)
                },
                onFailure = { AppResult.failure(DocumentError.FileNotFound) }
            )
    }

    override suspend fun openForWrite(uri: Uri): AppResult<OutputStream> = withContext(Dispatchers.IO) {
        runCatching { resolver.openOutputStream(uri, "rwt") }
            .fold(
                onSuccess = { stream ->
                    if (stream == null) AppResult.failure(DocumentError.PermissionDenied)
                    else AppResult.success(stream)
                },
                onFailure = { AppResult.failure(DocumentError.PermissionDenied) }
            )
    }

    override suspend fun create(name: String, mimeType: String): AppResult<Uri> {
        // NOTE: The actual system picker is launched by the UI layer via an ActivityResultLauncher.
        // This method documents the creation contract; real URI resolution happens in the launcher.
        return AppResult.failure(DocumentError.Generic("Use ACTION_CREATE_DOCUMENT launcher in UI"))
    }

    override suspend fun persistPermission(uri: Uri): AppResult<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            resolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )
        }.fold(
            onSuccess = { AppResult.success(Unit) },
            onFailure = { AppResult.failure(DocumentError.PermissionDenied) }
        )
    }

    override suspend fun queryName(uri: Uri): AppResult<String> = queryColumn(uri, OpenableColumns.DISPLAY_NAME)

    override suspend fun querySize(uri: Uri): AppResult<Long> {
        val raw = queryColumn(uri, OpenableColumns.SIZE)
        return raw.map { it.toLongOrNull() ?: 0L }
    }

    override suspend fun queryMimeType(uri: Uri): AppResult<String?> {
        return AppResult.success(runCatching { resolver.getType(uri) }.getOrNull())
    }

    override suspend fun copy(input: InputStream, output: OutputStream): AppResult<Long> =
        withContext(Dispatchers.IO) {
            runCatching {
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                var total = 0L
                var read = input.read(buffer)
                while (read >= 0) {
                    output.write(buffer, 0, read)
                    total += read
                    read = input.read(buffer)
                }
                output.flush()
                total
            }.fold(
                onSuccess = { AppResult.success(it) },
                onFailure = { AppResult.failure(DocumentError.SaveFailed) }
            )
        }

    private suspend fun queryColumn(uri: Uri, column: String): AppResult<String> =
        withContext(Dispatchers.IO) {
            runCatching {
                resolver.query(uri, arrayOf(column), null, null, null)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val index = cursor.getColumnIndex(column)
                        if (index >= 0) cursor.getString(index) else null
                    } else null
                }
            }.fold(
                onSuccess = { value -> AppResult.success(value ?: "") },
                onFailure = { AppResult.failure(DocumentError.FileNotFound) }
            )
        }

    companion object {
        private const val DEFAULT_BUFFER_SIZE = 8192
    }
}
