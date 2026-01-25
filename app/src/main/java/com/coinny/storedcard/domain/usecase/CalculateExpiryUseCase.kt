package com.coinny.storedcard.domain.usecase

import com.coinny.storedcard.data.local.entity.Card
import com.coinny.storedcard.data.repository.CardRepository
import com.coinny.storedcard.domain.model.CardStatus

class CalculateExpiryUseCase(private val cardRepository: CardRepository) {

    suspend fun checkAndUpdateExpiredCards(): List<Card> {
        val currentTime = System.currentTimeMillis()
        val expiredCards = cardRepository.getExpiredCards(currentTime)

        // 更新所有过期卡片的状态
        expiredCards.forEach { card ->
            if (card.status != CardStatus.EXPIRED) {
                val updatedCard = card.copy(
                    status = CardStatus.EXPIRED,
                    updatedAt = currentTime
                )
                cardRepository.updateCard(updatedCard)
            }
        }

        return expiredCards
    }

    suspend fun getExpiringCards(daysAhead: Int = 7): List<Card> {
        val currentTime = System.currentTimeMillis()
        val futureTime = currentTime + (daysAhead * 24 * 60 * 60 * 1000L)
        return cardRepository.getExpiringCards(currentTime, futureTime)
    }

    fun isCardExpired(card: Card): Boolean {
        return card.expiryDate?.let { it < System.currentTimeMillis() } ?: false
    }

    fun getDaysUntilExpiry(card: Card): Long? {
        return card.expiryDate?.let {
            val diff = it - System.currentTimeMillis()
            if (diff > 0) {
                diff / (24 * 60 * 60 * 1000L)
            } else {
                0
            }
        }
    }
}
