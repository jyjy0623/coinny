package com.coinny.storedcard.data.local

import androidx.room.TypeConverter
import com.coinny.storedcard.domain.model.CardStatus
import com.coinny.storedcard.domain.model.CardType
import com.coinny.storedcard.domain.model.TransactionType

class Converters {
    @TypeConverter
    fun fromCardType(value: CardType): String {
        return value.name
    }

    @TypeConverter
    fun toCardType(value: String): CardType {
        return CardType.valueOf(value)
    }

    @TypeConverter
    fun fromCardStatus(value: CardStatus): String {
        return value.name
    }

    @TypeConverter
    fun toCardStatus(value: String): CardStatus {
        return CardStatus.valueOf(value)
    }

    @TypeConverter
    fun fromTransactionType(value: TransactionType): String {
        return value.name
    }

    @TypeConverter
    fun toTransactionType(value: String): TransactionType {
        return TransactionType.valueOf(value)
    }
}
