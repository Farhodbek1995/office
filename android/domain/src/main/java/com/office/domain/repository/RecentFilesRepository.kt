package com.office.domain.repository

import com.office.core.common.AppResult
import com.office.domain.model.AppSettings
import com.office.domain.model.RecentFile
import kotlinx.coroutines.flow.Flow

/**
 * Repository for recent files metadata (reja.txt 22-bo'lim).
 */
interface RecentFilesRepository {
    fun observeRecentFiles(limit: Int = 50): Flow<List<RecentFile>>
    suspend fun getRecentFiles(limit: Int = 50): AppResult<List<RecentFile>>
    suspend fun addOrUpdateRecentFile(file: RecentFile): AppResult<Unit>
    suspend fun removeRecentFile(id: Long): AppResult<Unit>
    suspend fun clearAll(): AppResult<Unit>
}
