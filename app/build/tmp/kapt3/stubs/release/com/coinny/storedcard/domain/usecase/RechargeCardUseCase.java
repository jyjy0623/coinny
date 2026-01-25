package com.coinny.storedcard.domain.usecase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J*\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/coinny/storedcard/domain/usecase/RechargeCardUseCase;", "", "cardRepository", "Lcom/coinny/storedcard/data/repository/CardRepository;", "transactionRepository", "Lcom/coinny/storedcard/data/repository/TransactionRepository;", "(Lcom/coinny/storedcard/data/repository/CardRepository;Lcom/coinny/storedcard/data/repository/TransactionRepository;)V", "execute", "Lcom/coinny/storedcard/domain/usecase/RechargeCardUseCase$RechargeResult;", "cardId", "", "amount", "", "note", "", "(JDLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "RechargeResult", "app_release"})
public final class RechargeCardUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.coinny.storedcard.data.repository.CardRepository cardRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.coinny.storedcard.data.repository.TransactionRepository transactionRepository = null;
    
    public RechargeCardUseCase(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.repository.CardRepository cardRepository, @org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.repository.TransactionRepository transactionRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object execute(long cardId, double amount, @org.jetbrains.annotations.Nullable()
    java.lang.String note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.coinny.storedcard.domain.usecase.RechargeCardUseCase.RechargeResult> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/coinny/storedcard/domain/usecase/RechargeCardUseCase$RechargeResult;", "", "()V", "Error", "Success", "Lcom/coinny/storedcard/domain/usecase/RechargeCardUseCase$RechargeResult$Error;", "Lcom/coinny/storedcard/domain/usecase/RechargeCardUseCase$RechargeResult$Success;", "app_release"})
    public static abstract class RechargeResult {
        
        private RechargeResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/coinny/storedcard/domain/usecase/RechargeCardUseCase$RechargeResult$Error;", "Lcom/coinny/storedcard/domain/usecase/RechargeCardUseCase$RechargeResult;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"})
        public static final class Error extends com.coinny.storedcard.domain.usecase.RechargeCardUseCase.RechargeResult {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.coinny.storedcard.domain.usecase.RechargeCardUseCase.RechargeResult.Error copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0016"}, d2 = {"Lcom/coinny/storedcard/domain/usecase/RechargeCardUseCase$RechargeResult$Success;", "Lcom/coinny/storedcard/domain/usecase/RechargeCardUseCase$RechargeResult;", "card", "Lcom/coinny/storedcard/data/local/entity/Card;", "transaction", "Lcom/coinny/storedcard/data/local/entity/Transaction;", "(Lcom/coinny/storedcard/data/local/entity/Card;Lcom/coinny/storedcard/data/local/entity/Transaction;)V", "getCard", "()Lcom/coinny/storedcard/data/local/entity/Card;", "getTransaction", "()Lcom/coinny/storedcard/data/local/entity/Transaction;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"})
        public static final class Success extends com.coinny.storedcard.domain.usecase.RechargeCardUseCase.RechargeResult {
            @org.jetbrains.annotations.NotNull()
            private final com.coinny.storedcard.data.local.entity.Card card = null;
            @org.jetbrains.annotations.NotNull()
            private final com.coinny.storedcard.data.local.entity.Transaction transaction = null;
            
            public Success(@org.jetbrains.annotations.NotNull()
            com.coinny.storedcard.data.local.entity.Card card, @org.jetbrains.annotations.NotNull()
            com.coinny.storedcard.data.local.entity.Transaction transaction) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.coinny.storedcard.data.local.entity.Card getCard() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.coinny.storedcard.data.local.entity.Transaction getTransaction() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.coinny.storedcard.data.local.entity.Card component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.coinny.storedcard.data.local.entity.Transaction component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.coinny.storedcard.domain.usecase.RechargeCardUseCase.RechargeResult.Success copy(@org.jetbrains.annotations.NotNull()
            com.coinny.storedcard.data.local.entity.Card card, @org.jetbrains.annotations.NotNull()
            com.coinny.storedcard.data.local.entity.Transaction transaction) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
    }
}