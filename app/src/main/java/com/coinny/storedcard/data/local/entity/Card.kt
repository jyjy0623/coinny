package com.coinny.storedcard.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coinny.storedcard.domain.model.CardStatus
import com.coinny.storedcard.domain.model.CardType

@Entity(tableName = "card")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "type")
    val type: CardType,

    @ColumnInfo(name = "initial_value")
    val initialValue: Double,  // 初始金额/次数/天数

    @ColumnInfo(name = "current_value")
    val currentValue: Double,  // 当前余额/剩余次数/剩余天数

    @ColumnInfo(name = "expiry_date")
    val expiryDate: Long?,  // 过期时间（时间戳）

    @ColumnInfo(name = "status")
    val status: CardStatus,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "last_deduct_date")
    val lastDeductDate: Long? = null,  // 上次扣费日期（用于按天卡）

    @ColumnInfo(name = "daily_rate")
    val dailyRate: Double? = null  // 每天收费金额（仅用于按天卡）
)
