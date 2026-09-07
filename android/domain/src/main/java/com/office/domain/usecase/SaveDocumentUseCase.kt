package com.office.domain.usecase

import com.office.core.common.AppResult
import com.office.engine.api.DocumentDestination
import com.office.engine.api.DocumentSession
import com.office.domain.repository.DocumentRepository
import javax.inject.Inject

/**
 * Saves a document session (reja.txt 8-bo'lim).
 */
class SaveDocumentUseCase @Inject constructor(
    private val repository: DocumentRepository
) {
    suspend operator fun invoke(
        session: DocumentSession,
        destination: DocumentDestination? = null
    ): AppResult<Unit> = repository.save(session, destination)
}
