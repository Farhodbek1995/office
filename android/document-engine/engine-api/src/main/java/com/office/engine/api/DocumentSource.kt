package com.office.engine.api

import android.net.Uri
import java.io.File

/**
 * Abstraction over where a document comes from (reja.txt 10-bo'lim).
 */
sealed interface DocumentSource {
    data class UriSource(val uri: Uri) : DocumentSource
    data class FileSource(val file: File) : DocumentSource
    data class BytesSource(val bytes: ByteArray, val name: String) : DocumentSource
}
