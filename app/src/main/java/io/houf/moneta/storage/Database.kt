package io.houf.moneta.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Cache::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun cacheDao(): CacheDao
}
