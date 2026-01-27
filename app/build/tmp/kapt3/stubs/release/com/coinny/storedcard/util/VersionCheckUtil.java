package com.coinny.storedcard.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/coinny/storedcard/util/VersionCheckUtil;", "", "()V", "VERSION_CHECK_URL", "", "checkForUpdate", "Lcom/coinny/storedcard/domain/model/VersionInfo;", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkForUpdateMock", "app_release"})
public final class VersionCheckUtil {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String VERSION_CHECK_URL = "https://jyjy0623.github.io/coinny/version.json";
    @org.jetbrains.annotations.NotNull()
    public static final com.coinny.storedcard.util.VersionCheckUtil INSTANCE = null;
    
    private VersionCheckUtil() {
        super();
    }
    
    /**
     * 检查是否有新版本
     * 返回null表示没有新版本或检查失败
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object checkForUpdate(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.coinny.storedcard.domain.model.VersionInfo> $completion) {
        return null;
    }
    
    /**
     * 模拟版本检查（用于测试）
     * 实际使用时应该删除此方法，使用上面的checkForUpdate
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object checkForUpdateMock(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.coinny.storedcard.domain.model.VersionInfo> $completion) {
        return null;
    }
}