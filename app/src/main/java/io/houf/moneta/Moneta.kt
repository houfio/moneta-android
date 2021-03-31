package io.houf.moneta

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.houf.moneta.service.ApiService

@HiltAndroidApp
class Moneta : Application()

@Module
@InstallIn(SingletonComponent::class)
class MonetaModule {
    @Provides
    fun provideRequestService(@ApplicationContext context: Context) = ApiService(context)
}
