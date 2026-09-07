package com.office.engine.api

/**
 * Unified document format enumeration (reja.txt 10-bo'lim).
 */
enum class DocumentFormat {
    DOC,
    DOCX,
    XLS,
    XLSX,
    PDF,
    TXT;

    companion object {
        fun fromMimeType(mimeType: String?): DocumentFormat? = when (mimeType?.lowercase()) {
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -> DOCX
            "application/msword" -> DOC
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" -> XLSX
            "application/vnd.ms-excel" -> XLS
            "application/pdf" -> PDF
            "text/plain" -> TXT
            else -> null
        }

        fun fromExtension(extension: String?): DocumentFormat? = when (extension?.lowercase()?.removePrefix(".")) {
            "docx" -> DOCX
            "doc" -> DOC
            "xlsx" -> XLSX
            "xls" -> XLS
            "pdf" -> PDF
            "txt" -> TXT
            else -> null
        }
    }
}
