package com.office.core.common

/**
 * MIME type and document format detection helpers (reja.txt 35-bo'lim: MIME detection).
 * Kept free of Android framework classes so domain and engine modules can reuse them.
 */
object MimeTypes {

    const val DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    const val DOC = "application/msword"
    const val XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    const val XLS = "application/vnd.ms-excel"
    const val PDF = "application/pdf"
    const val TXT = "text/plain"

    private val extensionMap: Map<String, String> = mapOf(
        "docx" to DOCX,
        "doc" to DOC,
        "xlsx" to XLSX,
        "xls" to XLS,
        "pdf" to PDF,
        "txt" to TXT
    )

    fun fromExtension(extension: String?): String? {
        if (extension == null) return null
        return extensionMap[extension.lowercase().removePrefix(".")]
    }

    fun fromFileName(name: String?): String? {
        if (name.isNullOrBlank()) return null
        val dot = name.lastIndexOf('.')
        if (dot < 0 || dot == name.length - 1) return null
        return fromExtension(name.substring(dot + 1))
    }
}
