package com.office.data.repository

import com.office.core.common.AppResult
import com.office.core.common.DocumentError
import com.office.domain.repository.DocumentRepository
import com.office.engine.api.DocumentDestination
import com.office.engine.api.DocumentEngine
import com.office.engine.api.DocumentFormat
import com.office.engine.api.DocumentSession
import com.office.engine.api.DocumentSource
import kotlin.jvm.JvmSuppressWildcards
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Routes document operations to the engine that supports the given format.
 * This is the single entry point the UI/ViewModel layer talks to (Rule 10).
 */
@Singleton
class DocumentRepositoryImpl @Inject constructor(
    private val engines: Set<@JvmSuppressWildcards DocumentEngine>
) : DocumentRepository {

    override suspend fun open(
        source: DocumentSource,
        format: DocumentFormat
    ): AppResult<DocumentSession> {
        val engine = engines.firstOrNull { it.canHandle(format) }
            ?: return AppResult.failure(DocumentError.UnsupportedFormat)
        return engine.open(source).fold(
            onSuccess = { AppResult.success(it) },
            onFailure = { AppResult.failure(mapEngineError(it)) }
        )
    }

    override suspend fun save(
        session: DocumentSession,
        destination: DocumentDestination?
    ): AppResult<Unit> = session.save(destination).fold(
        onSuccess = { AppResult.success(Unit) },
        onFailure = { AppResult.failure(mapEngineError(it)) }
    )

    override suspend fun export(
        session: DocumentSession,
        format: DocumentFormat,
        destination: DocumentDestination
    ): AppResult<Unit> {
        val engine = engines.firstOrNull { it.canHandle(session.format) }
            ?: return AppResult.failure(DocumentError.UnsupportedFormat)
        return engine.export(session, format, destination).fold(
            onSuccess = { AppResult.success(Unit) },
            onFailure = { AppResult.failure(mapEngineError(it)) }
        )
    }

    override suspend fun close(session: DocumentSession): AppResult<Unit> {
        session.close()
        return AppResult.success(Unit)
    }

    private fun mapEngineError(t: Throwable): DocumentError = when (t) {
        is DocumentError -> t
        is OutOfMemoryError -> DocumentError.OutOfMemory
        else -> DocumentError.EngineError
    }
}
