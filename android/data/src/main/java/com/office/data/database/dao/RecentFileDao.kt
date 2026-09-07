package com.office.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.office.data.database.entity.RecentFileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentFileDao {

    @Query("SELECT * FROM recent_files ORDER BY last_opened DESC LIMIT :limit")
    fun observeRecentFiles(limit: Int): Flow<List<RecentFileEntity>>

    @Query("SELECT * FROM recent_files ORDER BY last_opened DESC LIMIT :limit")
    suspend fun getRecentFiles(limit: Int): List<RecentFileEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: RecentFileEntity): Long

    @Query("DELETE FROM recent_files WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM recent_files")
    suspend fun clearAll()
}
