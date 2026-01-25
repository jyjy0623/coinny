package com.coinny.storedcard.ui.common;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/coinny/storedcard/ui/common/UpdateDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "downloadReceiver", "Landroid/content/BroadcastReceiver;", "versionInfo", "Lcom/coinny/storedcard/domain/model/VersionInfo;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "", "startDownload", "Companion", "app_debug"})
public final class UpdateDialogFragment extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.Nullable()
    private com.coinny.storedcard.domain.model.VersionInfo versionInfo;
    @org.jetbrains.annotations.Nullable()
    private android.content.BroadcastReceiver downloadReceiver;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_VERSION_INFO = "version_info";
    @org.jetbrains.annotations.NotNull()
    public static final com.coinny.storedcard.ui.common.UpdateDialogFragment.Companion Companion = null;
    
    public UpdateDialogFragment() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void startDownload() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/coinny/storedcard/ui/common/UpdateDialogFragment$Companion;", "", "()V", "ARG_VERSION_INFO", "", "newInstance", "Lcom/coinny/storedcard/ui/common/UpdateDialogFragment;", "versionInfo", "Lcom/coinny/storedcard/domain/model/VersionInfo;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.coinny.storedcard.ui.common.UpdateDialogFragment newInstance(@org.jetbrains.annotations.NotNull()
        com.coinny.storedcard.domain.model.VersionInfo versionInfo) {
            return null;
        }
    }
}