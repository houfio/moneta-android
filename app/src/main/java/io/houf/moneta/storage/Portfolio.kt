package io.houf.moneta.storage

import androidx.room.*

@Entity(tableName = "portfolio")
data class Portfolio(
    @PrimaryKey val id: String,
    val amount: Double
)

@Dao
interface PortfolioDao {
    @Query("select * from portfolio")
    suspend fun get(): List<Portfolio>

    @Query("select * from portfolio where id = :id")
    suspend fun get(id: String): Portfolio?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(portfolio: Portfolio)
}
