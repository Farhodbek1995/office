package com.office.engine.api

/**
 * The heart of the document platform (reja.txt 9-bo'lim).
 * The API is independent from the Android UI layer; engines are connected
 * through adapters (Rule 10).
 */
interface DocumentEngine {

    val supportedFormats: Set<DocumentFormat>

    /** Whether this engine can open the given source. */
    fun canHandle(format: DocumentFormat): Boolean

    suspend fun open(source: DocumentSource): Result<DocumentSession>

    suspend fun export(
        session: DocumentSession,
        format: DocumentFormat,
        destination: DocumentDestination
    ): Result<Unit>
}
