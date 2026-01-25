package com.coinny.storedcard.ui.main

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.coinny.storedcard.data.local.AppDatabase
import com.coinny.storedcard.data.local.entity.Card
import com.coinny.storedcard.data.repository.CardRepository
import com.coinny.storedcard.data.repository.TransactionRepository
import com.coinny.storedcard.util.BackupUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(
    private val context: Context,
    private val cardRepository: CardRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    val cards: Flow<List<Card>> = cardRepository.getAllCards()

    fun exportBackup() {
        viewModelScope.launch {
            try {
                val allCards = cards.first()
                val allTransactions = transactionRepository.getAllTransactions().first()
                val file = BackupUtil.exportToJson(context, allCards, allTransactions)
                if (file != null) {
                    Toast.makeText(context, "备份成功：${file.absolutePath}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "备份失败", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "备份失败：${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    class Factory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val database = AppDatabase.getDatabase(context)
            val cardRepository = CardRepository(database.cardDao())
            val transactionRepository = TransactionRepository(database.transactionDao())
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(context, cardRepository, transactionRepository) as T
        }
    }
}
