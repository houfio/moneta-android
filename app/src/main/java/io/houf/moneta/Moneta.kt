package io.houf.moneta

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.houf.moneta.service.ApiService
import io.houf.moneta.storage.CacheDao
import io.houf.moneta.storage.Database

@HiltAndroidApp
class Moneta : Application()

@Module
@InstallIn(SingletonComponent::class)
class MonetaModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, Database::class.java, "Moneta").build()

    @Provides
    fun provideCacheDao(database: Database) = database.cacheDao()

    @Provides
    fun provideApiService(@ApplicationContext context: Context, cache: CacheDao) = ApiService(context, cache)
}
