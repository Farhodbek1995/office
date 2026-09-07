package com.office.domain.usecase

import com.office.core.common.AppResult
import com.office.engine.api.DocumentSession
import com.office.domain.repository.DocumentRepository
import javax.inject.Inject

/**
 * Closes a document session and releases engine resources (reja.txt 8-bo'lim).
 */
class CloseDocumentUseCase @Inject constructor(
    private val repository: DocumentRepository
) {
    suspend operator fun invoke(session: DocumentSession): AppResult<Unit> =
        repository.close(session)
}
