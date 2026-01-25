package com.coinny.storedcard.data.repository

import com.coinny.storedcard.data.local.dao.CardDao
import com.coinny.storedcard.data.local.entity.Card
import com.coinny.storedcard.domain.model.CardStatus
import kotlinx.coroutines.flow.Flow

class CardRepository(private val cardDao: CardDao) {

    fun getAllCards(): Flow<List<Card>> {
        return cardDao.getAllCards()
    }

    fun getCardById(cardId: Long): Flow<Card?> {
        return cardDao.getByIdFlow(cardId)
    }

    suspend fun getCardByIdSync(cardId: Long): Card? {
        return cardDao.getById(cardId)
    }

    fun getActiveCards(): Flow<List<Card>> {
        return cardDao.getActiveCards()
    }

    fun getCardsByStatus(status: CardStatus): Flow<List<Card>> {
        return cardDao.getCardsByStatus(status)
    }

    suspend fun insertCard(card: Card): Long {
        return cardDao.insert(card)
    }

    suspend fun updateCard(card: Card) {
        cardDao.update(card)
    }

    suspend fun deleteCard(card: Card) {
        cardDao.delete(card)
    }

    suspend fun getExpiredCards(timestamp: Long): List<Card> {
        return cardDao.getExpiredCards(timestamp)
    }

    suspend fun getExpiringCards(currentTime: Long, futureTime: Long): List<Card> {
        return cardDao.getExpiringCards(currentTime, futureTime)
    }
}
