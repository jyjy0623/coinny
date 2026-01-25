package com.coinny.storedcard.ui.addedit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coinny.storedcard.data.local.AppDatabase
import com.coinny.storedcard.data.local.entity.Card
import com.coinny.storedcard.data.local.entity.Transaction
import com.coinny.storedcard.data.repository.CardRepository
import com.coinny.storedcard.data.repository.TransactionRepository
import com.coinny.storedcard.domain.model.CardStatus
import com.coinny.storedcard.domain.model.CardType
import com.coinny.storedcard.domain.model.TransactionType

class AddEditCardViewModel(
    private val cardRepository: CardRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    suspend fun createCard(
        name: String,
        type: CardType,
        initialValue: Double,
        expiryDate: Long?,
        dailyRate: Double? = null
    ): Result<Long> {
        return try {
            val card = Card(
                name = name,
                type = type,
                initialValue = initialValue,
                currentValue = initialValue,
                expiryDate = expiryDate,
                status = CardStatus.ACTIVE,
                dailyRate = dailyRate
            )

            val cardId = cardRepository.insertCard(card)

            // 记录创建交易
            val transaction = Transaction(
                cardId = cardId,
                type = TransactionType.CREATE,
                amount = initialValue,
                note = "创建卡片"
            )
            transactionRepository.insertTransaction(transaction)

            Result.success(cardId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    class Factory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val database = AppDatabase.getDatabase(context)
            val cardRepository = CardRepository(database.cardDao())
            val transactionRepository = TransactionRepository(database.transactionDao())
            @Suppress("UNCHECKED_CAST")
            return AddEditCardViewModel(cardRepository, transactionRepository) as T
        }
    }
}
