package com.office.core.common

/**
 * Sealed error model shared across the whole application (reja.txt 31-bo'lim).
 * UI layer maps these to human-readable localized messages instead of raw exceptions.
 */
sealed class DocumentError : Exception() {

    data object FileNotFound : DocumentError()
    data object PermissionDenied : DocumentError()
    data object UnsupportedFormat : DocumentError()
    data object CorruptedDocument : DocumentError()
    data object EngineError : DocumentError()
    data object OutOfMemory : DocumentError()
    data object SaveFailed : DocumentError()
    data object EmptyDocument : DocumentError()

    data class Generic(override val message: String) : DocumentError()
}
