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

        // 检查卡片状态
        if (card.status == CardStatus.EXPIRED) {
            return DeductResult.Error("卡片已过期")
        }

        if (card.status == CardStatus.PAUSED) {
            return DeductResult.Error("卡片已暂停")
        }

        // 检查过期时间
        if (card.expiryDate != null && card.expiryDate < System.currentTimeMillis()) {
            val expiredCard = card.copy(status = CardStatus.EXPIRED)
            cardRepository.updateCard(expiredCard)
            return DeductResult.Error("卡片已过期")
        }

        // 根据卡片类型处理扣费
        val deductResult = when (card.type) {
            CardType.AMOUNT -> handleAmountDeduct(card, amount)
            CardType.COUNT -> handleCountDeduct(card, amount)
            CardType.DAILY -> handleDailyDeduct(card)
        }

        return when (deductResult) {
            is DeductResult.Error -> deductResult
            is DeductResult.Success -> {
                // 更新卡片
                cardRepository.updateCard(deductResult.card)

                // 记录交易，使用计算后的实际金额 deductResult.transaction.amount
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
        // 如果是新卡且从未扣费，则从创建时间开始算
        val lastDeductDate = card.lastDeductDate ?: card.createdAt

        // 计算经过的天数
        val daysPassed = TimeUnit.MILLISECONDS.toDays(currentTime - lastDeductDate)

        if (daysPassed < 1) {
            return DeductResult.Error("未满24小时，无需扣费")
        }

        val dailyRate = card.dailyRate ?: return DeductResult.Error("未设置每天收费金额")
        val totalDeduction = dailyRate * daysPassed

        if (card.currentValue < totalDeduction) {
            // 如果余额不足以支付全部天数，可以考虑扣除剩余所有余额或报错
            // 这里选择报错，由用户充值后再处理
            return DeductResult.Error("余额不足以支付 ${daysPassed} 天的费用")
        }

        val newValue = card.currentValue - totalDeduction
        val updatedCard = card.copy(
            currentValue = newValue,
            lastDeductDate = currentTime, // 更新最后扣费时间
            updatedAt = currentTime
        )

        return DeductResult.Success(updatedCard, Transaction(0, 0, TransactionType.DEDUCT, totalDeduction))
    }
}
