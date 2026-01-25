package com.coinny.storedcard.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eJ&\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/coinny/storedcard/util/NotificationUtil;", "", "()V", "CHANNEL_DESCRIPTION", "", "CHANNEL_ID", "CHANNEL_NAME", "createNotificationChannel", "", "context", "Landroid/content/Context;", "sendExpiryNotification", "cardName", "daysLeft", "", "sendLowBalanceNotification", "balance", "", "type", "app_debug"})
public final class NotificationUtil {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "stored_card_channel";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_NAME = "\u50a8\u503c\u5361\u63d0\u9192";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_DESCRIPTION = "\u50a8\u503c\u5361\u8fc7\u671f\u548c\u4f59\u989d\u4e0d\u8db3\u63d0\u9192";
    @org.jetbrains.annotations.NotNull()
    public static final com.coinny.storedcard.util.NotificationUtil INSTANCE = null;
    
    private NotificationUtil() {
        super();
    }
    
    public final void createNotificationChannel(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void sendExpiryNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String cardName, int daysLeft) {
    }
    
    public final void sendLowBalanceNotification(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String cardName, double balance, @org.jetbrains.annotations.NotNull()
    java.lang.String type) {
    }
}