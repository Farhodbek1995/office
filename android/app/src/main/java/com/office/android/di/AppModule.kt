package com.office.android.di

import com.office.core.common.DefaultDispatchersProvider
import com.office.core.common.DispatchersProvider
import com.office.core.filesystem.FileSystemManager
import com.office.core.filesystem.SafFileSystemManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindFileSystemManager(impl: SafFileSystemManager): FileSystemManager
}

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Singleton
    fun provideDispatchersProvider(): DispatchersProvider = DefaultDispatchersProvider()
}
