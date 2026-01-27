package com.coinny.storedcard.domain.usecase

import com.coinny.storedcard.data.local.entity.Card
import com.coinny.storedcard.data.local.entity.Transaction
import com.coinny.storedcard.data.repository.CardRepository
import com.coinny.storedcard.data.repository.TransactionRepository
import com.coinny.storedcard.domain.model.CardStatus
import com.coinny.storedcard.domain.model.CardType
import com.coinny.storedcard.domain.model.TransactionType
import java.util.concurrent.TimeUnit

class DeductCardUseCase(
    private val cardRepository: CardRepository,
    private val transactionRepository: TransactionRepository
) {
    sealed class DeductResult {
        data class Success(val card: Card, val transaction: Transaction) : DeductResult()
        data class Error(val message: String) : DeductResult()
    }

    suspend fun execute(cardId: Long, amount: Double, note: String? = null): DeductResult {
        val card = cardRepository.getCardByIdSync(cardId)
            ?: return DeductResult.Error("卡片不存在")

        if (card.status == CardStatus.EXPIRED) {
            return DeductResult.Error("卡片已过期")
        }

        if (card.status == CardStatus.PAUSED) {
            return DeductResult.Error("卡片已暂停")
        }

        if (card.expiryDate != null && card.expiryDate < System.currentTimeMillis()) {
            val expiredCard = card.copy(status = CardStatus.EXPIRED)
            cardRepository.updateCard(expiredCard)
            return DeductResult.Error("卡片已过期")
        }

        val deductResult = when (card.type) {
            CardType.AMOUNT -> handleAmountDeduct(card, amount)
            CardType.COUNT -> handleCountDeduct(card, amount)
            CardType.DAILY -> handleDailyDeduct(card)
        }

        return when (deductResult) {
            is DeductResult.Error -> deductResult
            is DeductResult.Success -> {
                cardRepository.updateCard(deductResult.card)

                val transaction = Transaction(
                    cardId = cardId,
                    type = TransactionType.DEDUCT,
                    amount = deductResult.transaction.amount,
                    note = note
                )
                val transactionId = transactionRepository.insertTransaction(transaction)

                DeductResult.Success(
                    deductResult.card,
                    transaction.copy(id = transactionId)
                )
            }
        }
    }

    private fun handleAmountDeduct(card: Card, amount: Double): DeductResult {
        if (card.currentValue < amount) {
            return DeductResult.Error("余额不足")
        }

        val newValue = card.currentValue - amount
        val updatedCard = card.copy(
            currentValue = newValue,
            updatedAt = System.currentTimeMillis()
        )

        return DeductResult.Success(updatedCard, Transaction(0, 0, TransactionType.DEDUCT, amount))
    }

    private fun handleCountDeduct(card: Card, count: Double): DeductResult {
        if (card.currentValue < count) {
            return DeductResult.Error("次数不足")
        }

        val newValue = card.currentValue - count
        val updatedCard = card.copy(
            currentValue = newValue,
            updatedAt = System.currentTimeMillis()
        )

        return DeductResult.Success(updatedCard, Transaction(0, 0, TransactionType.DEDUCT, count))
    }

    private fun handleDailyDeduct(card: Card): DeductResult {
        val currentTime = System.currentTimeMillis()
        val lastDeductDate = card.lastDeductDate ?: card.createdAt

        val diffMillis = currentTime - lastDeductDate
        val oneDayMillis = 24 * 60 * 60 * 1000L
        
        // 恢复为24小时周期，允许1小时容错（23小时即视为满一天）
        val daysPassed = (diffMillis + (1 * 60 * 60 * 1000L)) / oneDayMillis

        if (daysPassed < 1) {
            return DeductResult.Error("未满24小时扣费周期")
        }

        val dailyRate = card.dailyRate ?: return DeductResult.Error("未设置每天收费金额")
        val totalDeduction = dailyRate * daysPassed

        if (card.currentValue < totalDeduction) {
            return DeductResult.Error("余额不足以支付 ${daysPassed} 天的费用")
        }

        val newValue = card.currentValue - totalDeduction
        val nextDeductDate = lastDeductDate + (daysPassed * oneDayMillis)
        
        val updatedCard = card.copy(
            currentValue = newValue,
            lastDeductDate = nextDeductDate,
            updatedAt = currentTime
        )

        return DeductResult.Success(updatedCard, Transaction(0, 0, TransactionType.DEDUCT, totalDeduction))
    }
}
