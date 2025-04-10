package com.example.habitos.di

import android.content.Context
import com.example.habitos.data.api.AuthService
import com.example.habitos.data.api.HabitApiService
import com.example.habitos.data.repository.HabitRepository
import com.example.habitos.data.repository.HabitRepositoryImpl
import com.example.habitos.data.repository.TokenRepository
import com.example.habitos.data.source.LocalHabitDataSource
import com.example.habitos.data.source.RemoteHabitDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "http://192.168.1.188:3000/"  // Reemplaza con tu URL base

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
    fun provideRemoteHabitDataSource(habitApiService: HabitApiService): RemoteHabitDataSource {
        return RemoteHabitDataSource(habitApiService)
    }

    @Provides
    @Singleton
    fun provideHabitApiService(retrofit: Retrofit): HabitApiService {
        return retrofit.create(HabitApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthService(): AuthService {
        return AuthService()
    }

    @Provides
    @Singleton
    fun provideTokenRepository(@ApplicationContext context: Context): TokenRepository {
        return TokenRepository(context)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(15, TimeUnit.SECONDS)
        return builder.build()
    }
}