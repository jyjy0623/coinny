package com.coinny.storedcard.data.repository

import com.coinny.storedcard.data.local.dao.TransactionDao
import com.coinny.storedcard.data.local.entity.Transaction
import com.coinny.storedcard.domain.model.TransactionType
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {

    suspend fun insertTransaction(transaction: Transaction): Long {
        return transactionDao.insert(transaction)
    }

    fun getTransactionsByCardId(cardId: Long): Flow<List<Transaction>> {
        return transactionDao.getByCardId(cardId)
    }

    fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllTransactions()
    }

    fun getTransactionsByDateRange(startTime: Long, endTime: Long): Flow<List<Transaction>> {
        return transactionDao.getByDateRange(startTime, endTime)
    }

    fun getTransactionsByType(type: TransactionType): Flow<List<Transaction>> {
        return transactionDao.getByType(type)
    }

    suspend fun getTotalAmountByCardAndType(cardId: Long, type: TransactionType): Double {
        return transactionDao.getTotalAmountByCardAndType(cardId, type) ?: 0.0
    }

    suspend fun deleteTransactionsByCardId(cardId: Long) {
        transactionDao.deleteByCardId(cardId)
    }
}
