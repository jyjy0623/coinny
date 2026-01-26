package com.coinny.storedcard.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.coinny.storedcard.data.local.AppDatabase
import com.coinny.storedcard.data.repository.CardRepository
import com.coinny.storedcard.data.repository.TransactionRepository
import com.coinny.storedcard.domain.model.CardStatus
import com.coinny.storedcard.domain.model.CardType
import com.coinny.storedcard.domain.usecase.CalculateExpiryUseCase
import com.coinny.storedcard.domain.usecase.DeductCardUseCase
import com.coinny.storedcard.util.NotificationUtil
import kotlinx.coroutines.flow.first

class ExpiryCheckWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val database = AppDatabase.getDatabase(applicationContext)
            val cardRepository = CardRepository(database.cardDao())
            val transactionRepository = TransactionRepository(database.transactionDao())
            val expiryUseCase = CalculateExpiryUseCase(cardRepository)
            val deductUseCase = DeductCardUseCase(cardRepository, transactionRepository)

            // 1. 处理天数卡的自动扣费
            val allCards = cardRepository.getAllCards().first()
            allCards.forEach { card ->
                if (card.type == CardType.DAILY && card.status == CardStatus.ACTIVE) {
                    // 执行自动扣费逻辑
                    deductUseCase.execute(card.id, 0.0, "系统自动扣费")
                }
            }

            // 2. 检查并更新过期卡片
            expiryUseCase.checkAndUpdateExpiredCards()

            // 3. 发送即将过期通知（7天内）
            val expiringCards = expiryUseCase.getExpiringCards(7)
            expiringCards.forEach { card ->
                if (card.status == CardStatus.ACTIVE) {
                    val daysLeft = expiryUseCase.getDaysUntilExpiry(card)
                    if (daysLeft != null && daysLeft <= 7) {
                        NotificationUtil.sendExpiryNotification(
                            applicationContext,
                            card.name,
                            daysLeft.toInt()
                        )
                    }
                }
            }

            // 4. 发送余额不足通知（少于初始值的10%）
            // 重新获取最新数据（因为上面可能执行了扣费）
            val updatedCards = cardRepository.getAllCards().first()
            updatedCards.forEach { card ->
                if (card.status == CardStatus.ACTIVE) {
                    val threshold = card.initialValue * 0.1
                    if (card.currentValue <= threshold && card.currentValue > 0) {
                        NotificationUtil.sendLowBalanceNotification(
                            applicationContext,
                            card.name,
                            card.currentValue,
                            card.type.name
                        )
                    }
                }
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}
