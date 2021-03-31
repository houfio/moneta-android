package io.houf.moneta.service

import androidx.room.Database
import androidx.room.RoomDatabase
import io.houf.moneta.storage.Cache
import io.houf.moneta.storage.CacheDao

@Database(entities = [Cache::class], version = 1)
abstract class DatabaseService : RoomDatabase() {
    abstract fun cache(): CacheDao
}
