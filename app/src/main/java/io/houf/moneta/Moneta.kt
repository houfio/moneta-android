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
import io.houf.moneta.service.DatabaseService

@HiltAndroidApp
class Moneta : Application()

@Module
@InstallIn(SingletonComponent::class)
class MonetaModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DatabaseService::class.java, "Moneta").build()

    @Provides
    fun provideApi(@ApplicationContext context: Context, database: DatabaseService) =
        ApiService(context, database)
}
