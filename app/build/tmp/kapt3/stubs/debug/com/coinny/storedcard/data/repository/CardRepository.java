package com.coinny.storedcard.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bJ\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bJ\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000b2\u0006\u0010\u0014\u001a\u00020\u0015J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\u0017\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0012J$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\f2\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/coinny/storedcard/data/repository/CardRepository;", "", "cardDao", "Lcom/coinny/storedcard/data/local/dao/CardDao;", "(Lcom/coinny/storedcard/data/local/dao/CardDao;)V", "deleteCard", "", "card", "Lcom/coinny/storedcard/data/local/entity/Card;", "(Lcom/coinny/storedcard/data/local/entity/Card;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveCards", "Lkotlinx/coroutines/flow/Flow;", "", "getAllCards", "getCardById", "cardId", "", "getCardByIdSync", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCardsByStatus", "status", "Lcom/coinny/storedcard/domain/model/CardStatus;", "getExpiredCards", "timestamp", "getExpiringCards", "currentTime", "futureTime", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCard", "updateCard", "app_debug"})
public final class CardRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.coinny.storedcard.data.local.dao.CardDao cardDao = null;
    
    public CardRepository(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.dao.CardDao cardDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Card>> getAllCards() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.coinny.storedcard.data.local.entity.Card> getCardById(long cardId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCardByIdSync(long cardId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.coinny.storedcard.data.local.entity.Card> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Card>> getActiveCards() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Card>> getCardsByStatus(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.domain.model.CardStatus status) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertCard(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Card card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateCard(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Card card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteCard(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Card card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getExpiredCards(long timestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.coinny.storedcard.data.local.entity.Card>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getExpiringCards(long currentTime, long futureTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.coinny.storedcard.data.local.entity.Card>> $completion) {
        return null;
    }
}