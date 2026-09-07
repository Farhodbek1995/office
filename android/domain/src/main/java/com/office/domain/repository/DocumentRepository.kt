package com.office.domain.repository

import com.office.core.common.AppResult
import com.office.engine.api.DocumentDestination
import com.office.engine.api.DocumentEngine
import com.office.engine.api.DocumentFormat
import com.office.engine.api.DocumentSession
import com.office.engine.api.DocumentSource

/**
 * Routes document operations to the correct engine (reja.txt 9, 10-bo'lim).
 * The UI talks to this repository, never to concrete engines directly.
 */
interface DocumentRepository {

    /** Opens a document with the engine matching [format]. */
    suspend fun open(source: DocumentSource, format: DocumentFormat): AppResult<DocumentSession>

    /** Saves a session to the given destination (or its original source). */
    suspend fun save(session: DocumentSession, destination: DocumentDestination? = null): AppResult<Unit>

    /** Exports a session to a different format. */
    suspend fun export(
        session: DocumentSession,
        format: DocumentFormat,
        destination: DocumentDestination
    ): AppResult<Unit>

    /** Closes a session and releases engine resources. */
    suspend fun close(session: DocumentSession): AppResult<Unit>
}
