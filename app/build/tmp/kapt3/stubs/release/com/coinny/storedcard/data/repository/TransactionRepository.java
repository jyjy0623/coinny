package com.coinny.storedcard.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bJ\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b2\u0006\u0010\u0007\u001a\u00020\bJ\"\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bJ\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/coinny/storedcard/data/repository/TransactionRepository;", "", "transactionDao", "Lcom/coinny/storedcard/data/local/dao/TransactionDao;", "(Lcom/coinny/storedcard/data/local/dao/TransactionDao;)V", "deleteTransactionsByCardId", "", "cardId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTransactions", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/coinny/storedcard/data/local/entity/Transaction;", "getTotalAmountByCardAndType", "", "type", "Lcom/coinny/storedcard/domain/model/TransactionType;", "(JLcom/coinny/storedcard/domain/model/TransactionType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactionsByCardId", "getTransactionsByDateRange", "startTime", "endTime", "getTransactionsByType", "insertTransaction", "transaction", "(Lcom/coinny/storedcard/data/local/entity/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class TransactionRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.coinny.storedcard.data.local.dao.TransactionDao transactionDao = null;
    
    public TransactionRepository(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.dao.TransactionDao transactionDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Transaction>> getTransactionsByCardId(long cardId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Transaction>> getAllTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Transaction>> getTransactionsByDateRange(long startTime, long endTime) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.coinny.storedcard.data.local.entity.Transaction>> getTransactionsByType(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.domain.model.TransactionType type) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTotalAmountByCardAndType(long cardId, @org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.domain.model.TransactionType type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteTransactionsByCardId(long cardId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}