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
