package com.office.domain.usecase

import com.office.core.common.AppResult
import com.office.engine.api.DocumentDestination
import com.office.engine.api.DocumentEngine
import com.office.engine.api.DocumentFormat
import com.office.engine.api.DocumentSession
import com.office.engine.api.DocumentSource
import com.office.domain.repository.DocumentRepository
import javax.inject.Inject

/**
 * Opens a document using the matching engine (reja.txt 8-bo'lim).
 */
class OpenDocumentUseCase @Inject constructor(
    private val repository: DocumentRepository
) {
    suspend operator fun invoke(
        source: DocumentSource,
        format: DocumentFormat
    ): AppResult<DocumentSession> = repository.open(source, format)
}
