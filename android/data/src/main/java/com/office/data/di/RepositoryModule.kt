package com.office.data.di

import com.office.data.repository.DocumentRepositoryImpl
import com.office.data.repository.RecentFilesRepositoryImpl
import com.office.data.settings.SettingsRepositoryImpl
import com.office.domain.repository.DocumentRepository
import com.office.domain.repository.RecentFilesRepository
import com.office.domain.repository.SettingsRepository
import com.office.engine.api.DocumentEngine
import com.office.engine.libreoffice.LibreOfficeEngine
import com.office.engine.pdf.PdfRendererEngine
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRecentFilesRepository(impl: RecentFilesRepositoryImpl): RecentFilesRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository

    @Binds
    @Singleton
    abstract fun bindDocumentRepository(impl: DocumentRepositoryImpl): DocumentRepository
}

@Module
@InstallIn(SingletonComponent::class)
object EngineModule {

    @Provides
    @IntoSet
    fun providePdfEngine(engine: PdfRendererEngine): DocumentEngine = engine

    @Provides
    @IntoSet
    fun provideLibreOfficeEngine(engine: LibreOfficeEngine): DocumentEngine = engine
}
