package com.coinny.storedcard.ui.deduct

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coinny.storedcard.data.local.AppDatabase
import com.coinny.storedcard.data.repository.CardRepository
import com.coinny.storedcard.data.repository.TransactionRepository
import com.coinny.storedcard.domain.usecase.DeductCardUseCase
import com.coinny.storedcard.domain.usecase.RechargeCardUseCase

class DeductViewModel(
    private val deductCardUseCase: DeductCardUseCase,
    private val rechargeCardUseCase: RechargeCardUseCase
) : ViewModel() {

    suspend fun deductCard(cardId: Long, amount: Double, note: String?): Result<Unit> {
        return try {
            when (val result = deductCardUseCase.execute(cardId, amount, note)) {
                is DeductCardUseCase.DeductResult.Success -> Result.success(Unit)
                is DeductCardUseCase.DeductResult.Error -> Result.failure(Exception(result.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun rechargeCard(cardId: Long, amount: Double, note: String?): Result<Unit> {
        return try {
            when (val result = rechargeCardUseCase.execute(cardId, amount, note)) {
                is RechargeCardUseCase.RechargeResult.Success -> Result.success(Unit)
                is RechargeCardUseCase.RechargeResult.Error -> Result.failure(Exception(result.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    class Factory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val database = AppDatabase.getDatabase(context)
            val cardRepository = CardRepository(database.cardDao())
            val transactionRepository = TransactionRepository(database.transactionDao())
            val deductUseCase = DeductCardUseCase(cardRepository, transactionRepository)
            val rechargeUseCase = RechargeCardUseCase(cardRepository, transactionRepository)
            @Suppress("UNCHECKED_CAST")
            return DeductViewModel(deductUseCase, rechargeUseCase) as T
        }
    }
}
