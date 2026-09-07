package com.office.engine.api

/**
 * A live handle to an open document.
 */
interface DocumentSession {
    val id: String
    val format: DocumentFormat
    val name: String

    /** Saves the current state back to its source/destination. */
    suspend fun save(destination: DocumentDestination? = null): Result<Unit>

    /** Releases engine resources. */
    suspend fun close()
}
