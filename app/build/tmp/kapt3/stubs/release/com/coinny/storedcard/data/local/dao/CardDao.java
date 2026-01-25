package com.coinny.storedcard.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\b2\u0006\u0010\f\u001a\u00020\rH\'J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\u0011\u001a\u00020\u0012H\'J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u0014\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u000eJ$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u001b"}, d2 = {"Lcom/coinny/storedcard/data/local/dao/CardDao;", "", "delete", "", "card", "Lcom/coinny/storedcard/data/local/entity/Card;", "(Lcom/coinny/storedcard/data/local/entity/Card;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveCards", "Lkotlinx/coroutines/flow/Flow;", "", "getAllCards", "getById", "cardId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByIdFlow", "getCardsByStatus", "status", "Lcom/coinny/storedcard/domain/model/CardStatus;", "getExpiredCards", "timestamp", "getExpiringCards", "currentTime", "futureTime", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "update", "app_release"})
@androidx.room.Dao()
public abstract interface CardDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Card card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Card card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Card card, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM card WHERE id = :cardId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long cardId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.coinny.storedcard.data.local.entity.Card> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM card WHERE id = :cardId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.coinny.storedcard.data.local.entity.Card> getByIdFlow(long cardId);
    
    @androidx.room.Query(value = "SELECT * FROM card ORDER BY created_at DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Card>> getAllCards();
    
    @androidx.room.Query(value = "SELECT * FROM card WHERE status = :status ORDER BY created_at DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Card>> getCardsByStatus(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.domain.model.CardStatus status);
    
    @androidx.room.Query(value = "SELECT * FROM card WHERE status = \'ACTIVE\' ORDER BY created_at DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Card>> getActiveCards();
    
    @androidx.room.Query(value = "SELECT * FROM card WHERE expiry_date IS NOT NULL AND expiry_date <= :timestamp")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getExpiredCards(long timestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.coinny.storedcard.data.local.entity.Card>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM card WHERE expiry_date IS NOT NULL AND expiry_date > :currentTime AND expiry_date <= :futureTime")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getExpiringCards(long currentTime, long futureTime, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.coinny.storedcard.data.local.entity.Card>> $completion);
}