package com.coinny.storedcard.domain.usecase

import com.coinny.storedcard.data.local.entity.Card
import com.coinny.storedcard.data.local.entity.Transaction
import com.coinny.storedcard.data.repository.CardRepository
import com.coinny.storedcard.data.repository.TransactionRepository
import com.coinny.storedcard.domain.model.CardType
import com.coinny.storedcard.domain.model.TransactionType

class RechargeCardUseCase(
    private val cardRepository: CardRepository,
    private val transactionRepository: TransactionRepository
) {
    sealed class RechargeResult {
        data class Success(val card: Card, val transaction: Transaction) : RechargeResult()
        data class Error(val message: String) : RechargeResult()
    }

    suspend fun execute(cardId: Long, amount: Double, note: String? = null): RechargeResult {
        val card = cardRepository.getCardByIdSync(cardId)
            ?: return RechargeResult.Error("卡片不存在")

        if (amount <= 0) {
            return RechargeResult.Error("充值金额必须大于0")
        }

        // 计算新的余额
        val newValue = card.currentValue + amount
        val updatedCard = card.copy(
            currentValue = newValue,
            updatedAt = System.currentTimeMillis()
        )

        // 更新卡片
        cardRepository.updateCard(updatedCard)

        // 记录交易
        val transaction = Transaction(
            cardId = cardId,
            type = TransactionType.RECHARGE,
            amount = amount,
            note = note
        )
        val transactionId = transactionRepository.insertTransaction(transaction)

        return RechargeResult.Success(
            updatedCard,
            transaction.copy(id = transactionId)
        )
    }
}
