package com.coinny.storedcard.domain.model

enum class CardStatus {
    ACTIVE,   // 活跃
    PAUSED,   // 暂停（仅用于按天卡）
    EXPIRED   // 已过期
}
