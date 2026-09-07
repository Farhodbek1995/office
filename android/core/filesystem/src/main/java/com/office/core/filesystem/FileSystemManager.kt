package com.office.core.filesystem

import android.net.Uri
import com.office.core.common.AppResult
import java.io.InputStream
import java.io.OutputStream

/**
 * Abstraction over the Android Storage Access Framework (reja.txt 7-bo'lim).
 * The app works with content:// URIs and prefers streaming I/O over ByteArray
 * so large files are never fully loaded into RAM (Rule 4).
 */
interface FileSystemManager {

    /** Opens a readable stream for the given document URI. */
    suspend fun open(uri: Uri): AppResult<InputStream>

    /** Opens a writable output stream, truncating the target document. */
    suspend fun openForWrite(uri: Uri): AppResult<OutputStream>

    /** Creates a new document and returns its persistent URI. */
    suspend fun create(name: String, mimeType: String): AppResult<Uri>

    /** Persists read/write permission for the URI across reboots. */
    suspend fun persistPermission(uri: Uri): AppResult<Unit>

    /** Returns metadata (name, size, mime) for a content URI. */
    suspend fun queryName(uri: Uri): AppResult<String>

    suspend fun querySize(uri: Uri): AppResult<Long>

    suspend fun queryMimeType(uri: Uri): AppResult<String?>

    /** Streams all bytes from [input] into [output] without loading them in memory. */
    suspend fun copy(input: InputStream, output: OutputStream): AppResult<Long>
}
