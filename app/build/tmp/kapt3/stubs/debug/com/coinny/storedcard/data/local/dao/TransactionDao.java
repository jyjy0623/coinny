package com.coinny.storedcard.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0004\u001a\u00020\u0005H\'J$\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H\'J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0010\u001a\u00020\u0011H\'J \u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0017\u00a8\u0006\u0018"}, d2 = {"Lcom/coinny/storedcard/data/local/dao/TransactionDao;", "", "deleteByCardId", "", "cardId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTransactions", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/coinny/storedcard/data/local/entity/Transaction;", "getByCardId", "getByDateRange", "startTime", "endTime", "getByType", "type", "Lcom/coinny/storedcard/domain/model/TransactionType;", "getTotalAmountByCardAndType", "", "(JLcom/coinny/storedcard/domain/model/TransactionType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "transaction", "(Lcom/coinny/storedcard/data/local/entity/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface TransactionDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM transaction_record WHERE card_id = :cardId ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Transaction>> getByCardId(long cardId);
    
    @androidx.room.Query(value = "SELECT * FROM transaction_record ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Transaction>> getAllTransactions();
    
    @androidx.room.Query(value = "SELECT * FROM transaction_record WHERE timestamp >= :startTime AND timestamp <= :endTime ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Transaction>> getByDateRange(long startTime, long endTime);
    
    @androidx.room.Query(value = "SELECT * FROM transaction_record WHERE type = :type ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Transaction>> getByType(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.domain.model.TransactionType type);
    
    @androidx.room.Query(value = "SELECT SUM(amount) FROM transaction_record WHERE card_id = :cardId AND type = :type")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalAmountByCardAndType(long cardId, @org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.domain.model.TransactionType type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "DELETE FROM transaction_record WHERE card_id = :cardId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteByCardId(long cardId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}