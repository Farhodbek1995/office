package com.office.domain.model

import com.office.engine.api.DocumentFormat

/**
 * Domain representation of a recently opened file (reja.txt 22-bo'lim).
 * Kept free of Android framework types so the domain layer stays pure.
 */
data class RecentFile(
    val id: Long,
    val uri: String,
    val name: String,
    val mimeType: String,
    val lastOpened: Long,
    val lastModified: Long,
    val thumbnailPath: String?
) {
    val format: DocumentFormat?
        get() = DocumentFormat.fromMimeType(mimeType)
            ?: DocumentFormat.fromExtension(name.substringAfterLast('.', ""))
}
