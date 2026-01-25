package com.coinny.storedcard.ui.deduct;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0017H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lcom/coinny/storedcard/ui/deduct/DeductDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "_binding", "Lcom/coinny/storedcard/databinding/DialogDeductBinding;", "binding", "getBinding", "()Lcom/coinny/storedcard/databinding/DialogDeductBinding;", "cardId", "", "isRecharge", "", "viewModel", "Lcom/coinny/storedcard/ui/deduct/DeductViewModel;", "getViewModel", "()Lcom/coinny/storedcard/ui/deduct/DeductViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "processTransaction", "setupListeners", "Companion", "app_release"})
public final class DeductDialogFragment extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.Nullable()
    private com.coinny.storedcard.databinding.DialogDeductBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private long cardId = -1L;
    private boolean isRecharge = false;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_CARD_ID = "card_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_IS_RECHARGE = "is_recharge";
    @org.jetbrains.annotations.NotNull()
    public static final com.coinny.storedcard.ui.deduct.DeductDialogFragment.Companion Companion = null;
    
    public DeductDialogFragment() {
        super();
    }
    
    private final com.coinny.storedcard.databinding.DialogDeductBinding getBinding() {
        return null;
    }
    
    private final com.coinny.storedcard.ui.deduct.DeductViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void setupListeners() {
    }
    
    private final void processTransaction() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/coinny/storedcard/ui/deduct/DeductDialogFragment$Companion;", "", "()V", "ARG_CARD_ID", "", "ARG_IS_RECHARGE", "newInstance", "Lcom/coinny/storedcard/ui/deduct/DeductDialogFragment;", "cardId", "", "isRecharge", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.coinny.storedcard.ui.deduct.DeductDialogFragment newInstance(long cardId, boolean isRecharge) {
            return null;
        }
    }
}