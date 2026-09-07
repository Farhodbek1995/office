package com.office.domain.usecase

import com.office.core.common.AppResult
import com.office.engine.api.DocumentDestination
import com.office.engine.api.DocumentFormat
import com.office.engine.api.DocumentSession
import com.office.domain.repository.DocumentRepository
import javax.inject.Inject

/**
 * Exports a document to another format (reja.txt 26-bo'lim).
 */
class ExportDocumentUseCase @Inject constructor(
    private val repository: DocumentRepository
) {
    suspend operator fun invoke(
        session: DocumentSession,
        format: DocumentFormat,
        destination: DocumentDestination
    ): AppResult<Unit> = repository.export(session, format, destination)
}
