package com.square.lightstream.di

import android.app.Application
import androidx.room.Room
import com.square.lightstream.data.IDataSource
import com.square.lightstream.data.Repository
import com.square.lightstream.data.source.local.LocalCache
import com.square.lightstream.data.source.local.LocalDataSource
import com.square.lightstream.data.source.local.RMDao
import com.square.lightstream.data.source.local.ILocalDataSource
import com.square.lightstream.data.source.remote.IRemoteDataSource
import com.square.lightstream.data.source.remote.RemoteDataSource
import com.square.lightstream.data.source.remote.RMService
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.Module
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    private companion object{
        const val DATABASE_NAME: String = "rm-database"
        const val BASE_URL: String = "https://rickandmortyapi.com"
    }

    @Provides
    @Singleton
    fun providesLocalCache(application: Application): LocalCache =
        Room.databaseBuilder(
            application,
            LocalCache::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun providesRMDao(localCache: LocalCache) : RMDao = localCache.RMDao()

    @Provides
    @Singleton
    fun providesOkHttp() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRMService(retrofit: Retrofit): RMService = retrofit.create(RMService::class.java)

    @Provides
    @Singleton
    fun provideLocalDataSource(localDataSource: LocalDataSource): ILocalDataSource = localDataSource

    @Provides
    @Singleton
    fun provideRemoteDataSource(remoteDataSource: RemoteDataSource): IRemoteDataSource = remoteDataSource

    @Provides
    @Singleton
    fun provideRepository(repository: Repository): IDataSource = repository
}