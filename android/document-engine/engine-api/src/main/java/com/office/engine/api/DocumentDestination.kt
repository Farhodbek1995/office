package com.office.engine.api

import java.io.File

/**
 * A lightweight destination for save/export operations.
 */
sealed interface DocumentDestination {
    data class UriDestination(val uri: android.net.Uri) : DocumentDestination
    data class FileDestination(val file: File) : DocumentDestination
}
