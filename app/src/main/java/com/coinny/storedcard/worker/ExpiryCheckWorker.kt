package com.coinny.storedcard.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.coinny.storedcard.data.local.AppDatabase
import com.coinny.storedcard.data.repository.CardRepository
import com.coinny.storedcard.domain.model.CardStatus
import com.coinny.storedcard.domain.usecase.CalculateExpiryUseCase
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
            val expiryUseCase = CalculateExpiryUseCase(cardRepository)

            // 检查并更新过期卡片
            expiryUseCase.checkAndUpdateExpiredCards()

            // 获取即将过期的卡片（7天内）
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

            // 检查余额不足的卡片（少于初始值的10%）
            val allCards = cardRepository.getAllCards().first()
            allCards.forEach { card ->
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
