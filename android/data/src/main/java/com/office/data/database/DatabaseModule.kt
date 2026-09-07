package com.office.data.database

import android.content.Context
import androidx.room.Room
import com.office.data.database.dao.RecentFileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideOfficeDatabase(@ApplicationContext context: Context): OfficeDatabase =
        Room.databaseBuilder(
            context,
            OfficeDatabase::class.java,
            "office_database.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideRecentFileDao(db: OfficeDatabase): RecentFileDao = db.recentFileDao()
}
