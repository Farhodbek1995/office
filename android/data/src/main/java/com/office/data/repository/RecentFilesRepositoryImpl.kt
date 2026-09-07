package com.office.data.repository

import com.office.core.common.AppResult
import com.office.data.database.dao.RecentFileDao
import com.office.data.database.entity.toDomain
import com.office.data.database.entity.toEntity
import com.office.domain.model.RecentFile
import com.office.domain.repository.RecentFilesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecentFilesRepositoryImpl @Inject constructor(
    private val dao: RecentFileDao
) : RecentFilesRepository {

    override fun observeRecentFiles(limit: Int): Flow<List<RecentFile>> =
        dao.observeRecentFiles(limit).map { entities -> entities.map { it.toDomain() } }

    override suspend fun getRecentFiles(limit: Int): AppResult<List<RecentFile>> =
        runCatching { dao.getRecentFiles(limit).map { it.toDomain() } }
            .fold(
                onSuccess = { AppResult.success(it) },
                onFailure = { AppResult.failure(com.office.core.common.DocumentError.Generic(it.message ?: "DB error")) }
            )

    override suspend fun addOrUpdateRecentFile(file: RecentFile): AppResult<Unit> =
        runCatching { dao.upsert(file.toEntity()) }
            .fold(
                onSuccess = { AppResult.success(Unit) },
                onFailure = { AppResult.failure(com.office.core.common.DocumentError.Generic(it.message ?: "DB error")) }
            )

    override suspend fun removeRecentFile(id: Long): AppResult<Unit> =
        runCatching { dao.deleteById(id) }
            .fold(
                onSuccess = { AppResult.success(Unit) },
                onFailure = { AppResult.failure(com.office.core.common.DocumentError.Generic(it.message ?: "DB error")) }
            )

    override suspend fun clearAll(): AppResult<Unit> =
        runCatching { dao.clearAll() }
            .fold(
                onSuccess = { AppResult.success(Unit) },
                onFailure = { AppResult.failure(com.office.core.common.DocumentError.Generic(it.message ?: "DB error")) }
            )
}
