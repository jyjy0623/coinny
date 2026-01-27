package com.coinny.storedcard.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011J\"\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000f0\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/coinny/storedcard/util/UpdateUtil;", "", "()V", "downloadId", "", "downloadApk", "context", "Landroid/content/Context;", "downloadUrl", "", "versionName", "getVersionCode", "", "getVersionName", "installApk", "", "file", "Ljava/io/File;", "registerDownloadReceiver", "Landroid/content/BroadcastReceiver;", "onDownloadComplete", "Lkotlin/Function1;", "app_release"})
public final class UpdateUtil {
    private static long downloadId = -1L;
    @org.jetbrains.annotations.NotNull()
    public static final com.coinny.storedcard.util.UpdateUtil INSTANCE = null;
    
    private UpdateUtil() {
        super();
    }
    
    /**
     * 获取当前应用版本号
     */
    public final int getVersionCode(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return 0;
    }
    
    /**
     * 获取当前应用版本名
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVersionName(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * 下载APK文件
     */
    public final long downloadApk(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String downloadUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String versionName) {
        return 0L;
    }
    
    /**
     * 安装APK
     */
    public final void installApk(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.io.File file) {
    }
    
    /**
     * 注册下载完成广播接收器
     */
    @org.jetbrains.annotations.NotNull()
    public final android.content.BroadcastReceiver registerDownloadReceiver(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.io.File, kotlin.Unit> onDownloadComplete) {
        return null;
    }
}