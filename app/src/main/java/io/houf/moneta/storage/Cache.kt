package io.houf.moneta.storage

import androidx.room.*

@Entity(tableName = "cache")
data class Cache(
    @PrimaryKey val id: String,
    val data: String
)

@Dao
interface CacheDao {
    @Query("select * from cache where id = :id")
    suspend fun get(id: String): Cache?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cache: Cache)
}
