package io.houf.moneta.service

import androidx.room.Database
import androidx.room.RoomDatabase
import io.houf.moneta.storage.Cache
import io.houf.moneta.storage.CacheDao
import io.houf.moneta.storage.Portfolio
import io.houf.moneta.storage.PortfolioDao

@Database(entities = [Cache::class, Portfolio::class], version = 1)
abstract class DatabaseService : RoomDatabase() {
    abstract fun cache(): CacheDao

    abstract fun portfolio(): PortfolioDao
}
