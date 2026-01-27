package com.coinny.storedcard.ui.carddetail

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
import com.coinny.storedcard.domain.model.TransactionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CardDetailViewModel(
    private val cardRepository: CardRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _card = MutableStateFlow<Card?>(null)
    val card: StateFlow<Card?> = _card.asStateFlow()

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()

    fun loadCard(cardId: Long) {
        viewModelScope.launch {
            cardRepository.getCardById(cardId).collect { card ->
                _card.value = card
            }
        }

        viewModelScope.launch {
            transactionRepository.getTransactionsByCardId(cardId).collect { transactions ->
                _transactions.value = transactions
            }
        }
    }

    suspend fun togglePauseResume() {
        val currentCard = _card.value ?: return
        val newStatus = if (currentCard.status == CardStatus.PAUSED) {
            CardStatus.ACTIVE
        } else {
            CardStatus.PAUSED
        }

        val updatedCard = currentCard.copy(
            status = newStatus,
            updatedAt = System.currentTimeMillis()
        )
        cardRepository.updateCard(updatedCard)
    }

    suspend fun deleteCard() {
        _card.value?.let {
            cardRepository.deleteCard(it)
        }
    }

    suspend fun deleteTransaction(transaction: Transaction) {
        val currentCard = _card.value ?: return
        
        // 1. 删除交易记录
        transactionRepository.deleteTransaction(transaction)
        
        // 2. 补偿卡片余额
        val amountAdjustment = when (transaction.type) {
            TransactionType.DEDUCT -> transaction.amount
            TransactionType.RECHARGE -> -transaction.amount
            TransactionType.CREATE -> -transaction.amount
        }
        
        if (amountAdjustment != 0.0) {
            val updatedCard = currentCard.copy(
                currentValue = currentCard.currentValue + amountAdjustment,
                updatedAt = System.currentTimeMillis()
            )
            cardRepository.updateCard(updatedCard)
        }
    }

    suspend fun updateTransactionAmount(transaction: Transaction, newAmount: Double) {
        val currentCard = _card.value ?: return
        
        // 1. 计算差额并更新卡片余额 (自动对账)
        val diff = newAmount - transaction.amount
        val amountAdjustment = when (transaction.type) {
            TransactionType.DEDUCT -> -diff
            TransactionType.RECHARGE -> diff
            TransactionType.CREATE -> diff
        }
        
        if (amountAdjustment != 0.0) {
            val updatedCard = currentCard.copy(
                currentValue = currentCard.currentValue + amountAdjustment,
                updatedAt = System.currentTimeMillis()
            )
            cardRepository.updateCard(updatedCard)
        }

        // 2. 更新交易记录
        val updatedTransaction = transaction.copy(
            amount = newAmount,
            timestamp = System.currentTimeMillis()
        )
        transactionRepository.updateTransaction(updatedTransaction)
    }

    class Factory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val database = AppDatabase.getDatabase(context)
            val cardRepository = CardRepository(database.cardDao())
            val transactionRepository = TransactionRepository(database.transactionDao())
            @Suppress("UNCHECKED_CAST")
            return CardDetailViewModel(cardRepository, transactionRepository) as T
        }
    }
}
