package com.coinny.storedcard.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\b\u001a\u00020\tH\'J$\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\'J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0015\u001a\u00020\u0016H\'J \u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u001c"}, d2 = {"Lcom/coinny/storedcard/data/local/dao/TransactionDao;", "", "delete", "", "transaction", "Lcom/coinny/storedcard/data/local/entity/Transaction;", "(Lcom/coinny/storedcard/data/local/entity/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteByCardId", "cardId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTransactions", "Lkotlinx/coroutines/flow/Flow;", "", "getByCardId", "getByDateRange", "startTime", "endTime", "getById", "id", "getByType", "type", "Lcom/coinny/storedcard/domain/model/TransactionType;", "getTotalAmountByCardAndType", "", "(JLcom/coinny/storedcard/domain/model/TransactionType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "update", "app_release"})
@androidx.room.Dao()
public abstract interface TransactionDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM transaction_record WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.coinny.storedcard.data.local.entity.Transaction> $completion);
    
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