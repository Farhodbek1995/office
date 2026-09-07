package com.office.domain.usecase

import com.office.core.common.AppResult
import com.office.domain.model.RecentFile
import com.office.domain.repository.RecentFilesRepository
import javax.inject.Inject

/**
 * Registers/updates a recently opened file after a successful open (reja.txt 22-bo'lim).
 */
class AddRecentFileUseCase @Inject constructor(
    private val repository: RecentFilesRepository
) {
    suspend operator fun invoke(file: RecentFile): AppResult<Unit> =
        repository.addOrUpdateRecentFile(file)
}
