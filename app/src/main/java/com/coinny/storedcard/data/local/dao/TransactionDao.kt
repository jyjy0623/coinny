package com.coinny.storedcard.data.local.dao

import androidx.room.*
import com.coinny.storedcard.data.local.entity.Transaction
import com.coinny.storedcard.domain.model.TransactionType
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction): Long

    @Query("SELECT * FROM transaction_record WHERE card_id = :cardId ORDER BY timestamp DESC")
    fun getByCardId(cardId: Long): Flow<List<Transaction>>

    @Query("SELECT * FROM transaction_record ORDER BY timestamp DESC")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM transaction_record WHERE timestamp >= :startTime AND timestamp <= :endTime ORDER BY timestamp DESC")
    fun getByDateRange(startTime: Long, endTime: Long): Flow<List<Transaction>>

    @Query("SELECT * FROM transaction_record WHERE type = :type ORDER BY timestamp DESC")
    fun getByType(type: TransactionType): Flow<List<Transaction>>

    @Query("SELECT SUM(amount) FROM transaction_record WHERE card_id = :cardId AND type = :type")
    suspend fun getTotalAmountByCardAndType(cardId: Long, type: TransactionType): Double?

    @Query("DELETE FROM transaction_record WHERE card_id = :cardId")
    suspend fun deleteByCardId(cardId: Long)
}
