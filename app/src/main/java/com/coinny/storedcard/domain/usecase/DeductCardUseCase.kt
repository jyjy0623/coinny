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
        data class Success(val card: Card, val transactions: List<Transaction>) : DeductResult()
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
            CardType.AMOUNT -> handleAmountDeduct(card, amount, note)
            CardType.COUNT -> handleCountDeduct(card, amount, note)
            CardType.DAILY -> handleDailyDeduct(card)
        }

        return when (deductResult) {
            is DeductResult.Error -> deductResult
            is DeductResult.Success -> {
                // 更新卡片最终状态
                cardRepository.updateCard(deductResult.card)

                // 批量记录所有交易
                deductResult.transactions.forEach { transaction ->
                    transactionRepository.insertTransaction(transaction)
                }

                DeductResult.Success(
                    deductResult.card,
                    deductResult.transactions
                )
            }
        }
    }

    private fun handleAmountDeduct(card: Card, amount: Double, note: String?): DeductResult {
        if (card.currentValue < amount) {
            return DeductResult.Error("余额不足")
        }

        val newValue = card.currentValue - amount
        val updatedCard = card.copy(
            currentValue = newValue,
            updatedAt = System.currentTimeMillis()
        )
        val transaction = Transaction(
            cardId = card.id,
            type = TransactionType.DEDUCT,
            amount = amount,
            note = note,
            timestamp = System.currentTimeMillis()
        )

        return DeductResult.Success(updatedCard, listOf(transaction))
    }

    private fun handleCountDeduct(card: Card, count: Double, note: String?): DeductResult {
        if (card.currentValue < count) {
            return DeductResult.Error("次数不足")
        }

        val newValue = card.currentValue - count
        val updatedCard = card.copy(
            currentValue = newValue,
            updatedAt = System.currentTimeMillis()
        )
        val transaction = Transaction(
            cardId = card.id,
            type = TransactionType.DEDUCT,
            amount = count,
            note = note,
            timestamp = System.currentTimeMillis()
        )

        return DeductResult.Success(updatedCard, listOf(transaction))
    }

    private fun handleDailyDeduct(card: Card): DeductResult {
        val currentTime = System.currentTimeMillis()
        val lastDeductDate = card.lastDeductDate ?: card.createdAt

        val diffMillis = currentTime - lastDeductDate
        val oneDayMillis = 24 * 60 * 60 * 1000L
        
        // 计算经过的天数（包含 1 小时容错）
        val daysPassed = (diffMillis + (1 * 60 * 60 * 1000L)) / oneDayMillis

        if (daysPassed < 1) {
            return DeductResult.Error("未满24小时扣费周期")
        }

        val dailyRate = card.dailyRate ?: return DeductResult.Error("未设置每天收费金额")
        val totalDeduction = dailyRate * daysPassed

        if (card.currentValue < totalDeduction) {
            return DeductResult.Error("余额不足以支付 ${daysPassed} 天的费用")
        }

        // 按天拆分交易记录
        val transactions = mutableListOf<Transaction>()
        var currentBalance = card.currentValue
        
        for (i in 1..daysPassed.toInt()) {
            currentBalance -= dailyRate
            val transactionTime = lastDeductDate + (i * oneDayMillis)
            transactions.add(
                Transaction(
                    cardId = card.id,
                    type = TransactionType.DEDUCT,
                    amount = dailyRate,
                    note = "系统自动扣费",
                    timestamp = if (transactionTime < currentTime) transactionTime else currentTime
                )
            )
        }

        val updatedCard = card.copy(
            currentValue = currentBalance,
            lastDeductDate = lastDeductDate + (daysPassed * oneDayMillis),
            updatedAt = currentTime
        )

        return DeductResult.Success(updatedCard, transactions)
    }
}
