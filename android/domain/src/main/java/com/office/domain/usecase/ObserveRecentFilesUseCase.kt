package com.office.domain.usecase

import com.office.core.common.AppResult
import com.office.domain.repository.RecentFilesRepository
import com.office.domain.model.RecentFile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Observes recent files for the home screen (reja.txt 22-bo'lim).
 */
class ObserveRecentFilesUseCase @Inject constructor(
    private val repository: RecentFilesRepository
) {
    operator fun invoke(limit: Int = 50): Flow<List<RecentFile>> =
        repository.observeRecentFiles(limit)
}
