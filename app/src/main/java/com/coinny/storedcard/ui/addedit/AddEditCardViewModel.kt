package com.coinny.storedcard.ui.addedit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.coinny.storedcard.data.local.AppDatabase
import com.coinny.storedcard.data.local.entity.Card
import com.coinny.storedcard.data.local.entity.Transaction
import com.coinny.storedcard.data.repository.CardRepository
import com.coinny.storedcard.data.repository.TransactionRepository
import com.coinny.storedcard.domain.model.CardStatus
import com.coinny.storedcard.domain.model.CardType
import com.coinny.storedcard.domain.model.TransactionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddEditCardViewModel(
    private val cardRepository: CardRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _card = MutableStateFlow<Card?>(null)
    val card: StateFlow<Card?> = _card

    fun loadCard(cardId: Long) {
        viewModelScope.launch {
            _card.value = cardRepository.getCardByIdSync(cardId)
        }
    }

    suspend fun saveCard(
        name: String,
        type: CardType,
        newValue: Double,
        expiryDate: Long?,
        dailyRate: Double? = null
    ): Result<Long> {
        return try {
            val currentCard = _card.value
            if (currentCard == null) {
                // 创建新卡片
                val card = Card(
                    name = name,
                    type = type,
                    initialValue = newValue,
                    currentValue = newValue,
                    expiryDate = expiryDate,
                    status = CardStatus.ACTIVE,
                    dailyRate = dailyRate
                )

                val cardId = cardRepository.insertCard(card)

                // 记录创建交易
                val transaction = Transaction(
                    cardId = cardId,
                    type = TransactionType.CREATE,
                    amount = newValue,
                    note = "创建卡片"
                )
                transactionRepository.insertTransaction(transaction)
                Result.success(cardId)
            } else {
                // 修改余额逻辑
                val diff = newValue - currentCard.currentValue
                if (diff != 0.0) {
                    // 生成一笔修改余额的记录
                    val transactionType = if (diff > 0) TransactionType.RECHARGE else TransactionType.DEDUCT
                    val transaction = Transaction(
                        cardId = currentCard.id,
                        type = transactionType,
                        amount = Math.abs(diff),
                        note = "手动修改余额",
                        timestamp = System.currentTimeMillis()
                    )
                    transactionRepository.insertTransaction(transaction)
                }

                // 更新现有卡片
                val updatedCard = currentCard.copy(
                    name = name,
                    currentValue = newValue, // 更新为新输入的当前余额
                    expiryDate = expiryDate,
                    dailyRate = dailyRate,
                    updatedAt = System.currentTimeMillis()
                )
                cardRepository.updateCard(updatedCard)
                Result.success(updatedCard.id)
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
            @Suppress("UNCHECKED_CAST")
            return AddEditCardViewModel(cardRepository, transactionRepository) as T
        }
    }
}
