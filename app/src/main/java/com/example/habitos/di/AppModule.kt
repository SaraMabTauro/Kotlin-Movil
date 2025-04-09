package com.example.habitos.di

import android.content.Context
import com.example.habitos.data.repository.HabitRepository
import com.example.habitos.data.repository.HabitRepositoryImpl
import com.example.habitos.data.source.LocalHabitDataSource
import com.example.habitos.data.source.RemoteHabitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHabitRepository(
        localDataSource: LocalHabitDataSource,
        remoteDataSource: RemoteHabitDataSource
    ): HabitRepository {
        return HabitRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideLocalHabitDataSource(@ApplicationContext context: Context): LocalHabitDataSource {
        return LocalHabitDataSource(context)
    }

    @Provides
    @Singleton
    fun provideRemoteHabitDataSource(): RemoteHabitDataSource {
        return RemoteHabitDataSource()
    }
}