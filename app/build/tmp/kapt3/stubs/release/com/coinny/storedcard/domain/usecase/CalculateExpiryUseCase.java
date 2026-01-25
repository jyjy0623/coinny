package com.coinny.storedcard.domain.usecase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0086@\u00a2\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/coinny/storedcard/domain/usecase/CalculateExpiryUseCase;", "", "cardRepository", "Lcom/coinny/storedcard/data/repository/CardRepository;", "(Lcom/coinny/storedcard/data/repository/CardRepository;)V", "checkAndUpdateExpiredCards", "", "Lcom/coinny/storedcard/data/local/entity/Card;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDaysUntilExpiry", "", "card", "(Lcom/coinny/storedcard/data/local/entity/Card;)Ljava/lang/Long;", "getExpiringCards", "daysAhead", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isCardExpired", "", "app_release"})
public final class CalculateExpiryUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.coinny.storedcard.data.repository.CardRepository cardRepository = null;
    
    public CalculateExpiryUseCase(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.repository.CardRepository cardRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object checkAndUpdateExpiredCards(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.coinny.storedcard.data.local.entity.Card>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getExpiringCards(int daysAhead, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.coinny.storedcard.data.local.entity.Card>> $completion) {
        return null;
    }
    
    public final boolean isCardExpired(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Card card) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getDaysUntilExpiry(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.data.local.entity.Card card) {
        return null;
    }
}