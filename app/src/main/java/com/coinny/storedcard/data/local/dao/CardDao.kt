package com.coinny.storedcard.data.local.dao

import androidx.room.*
import com.coinny.storedcard.data.local.entity.Card
import com.coinny.storedcard.domain.model.CardStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: Card): Long

    @Update
    suspend fun update(card: Card)

    @Delete
    suspend fun delete(card: Card)

    @Query("SELECT * FROM card WHERE id = :cardId")
    suspend fun getById(cardId: Long): Card?

    @Query("SELECT * FROM card WHERE id = :cardId")
    fun getByIdFlow(cardId: Long): Flow<Card?>

    @Query("SELECT * FROM card ORDER BY created_at DESC")
    fun getAllCards(): Flow<List<Card>>

    @Query("SELECT * FROM card WHERE status = :status ORDER BY created_at DESC")
    fun getCardsByStatus(status: CardStatus): Flow<List<Card>>

    @Query("SELECT * FROM card WHERE status = 'ACTIVE' ORDER BY created_at DESC")
    fun getActiveCards(): Flow<List<Card>>

    @Query("SELECT * FROM card WHERE expiry_date IS NOT NULL AND expiry_date <= :timestamp")
    suspend fun getExpiredCards(timestamp: Long): List<Card>

    @Query("SELECT * FROM card WHERE expiry_date IS NOT NULL AND expiry_date > :currentTime AND expiry_date <= :futureTime")
    suspend fun getExpiringCards(currentTime: Long, futureTime: Long): List<Card>
}
