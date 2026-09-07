package com.office.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.office.data.database.dao.RecentFileDao
import com.office.data.database.entity.RecentFileEntity

@Database(
    entities = [RecentFileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OfficeDatabase : RoomDatabase() {
    abstract fun recentFileDao(): RecentFileDao
}
