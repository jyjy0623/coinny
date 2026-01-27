package com.coinny.storedcard.ui.carddetail;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u000e\u000fB\u0005\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\tH\u0016\u00a8\u0006\u0010"}, d2 = {"Lcom/coinny/storedcard/ui/carddetail/TransactionAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/coinny/storedcard/data/local/entity/Transaction;", "Lcom/coinny/storedcard/ui/carddetail/TransactionAdapter$TransactionViewHolder;", "()V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "TransactionDiffCallback", "TransactionViewHolder", "app_release"})
public final class TransactionAdapter extends androidx.recyclerview.widget.ListAdapter<com.coinny.storedcard.data.local.entity.Transaction, com.coinny.storedcard.ui.carddetail.TransactionAdapter.TransactionViewHolder> {
    
    public TransactionAdapter() {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.coinny.storedcard.ui.carddetail.TransactionAdapter.TransactionViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.coinny.storedcard.ui.carddetail.TransactionAdapter.TransactionViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/coinny/storedcard/ui/carddetail/TransactionAdapter$TransactionDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/coinny/storedcard/data/local/entity/Transaction;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"})
    public static final class TransactionDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.coinny.storedcard.data.local.entity.Transaction> {
        
        public TransactionDiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.coinny.storedcard.data.local.entity.Transaction oldItem, @org.jetbrains.annotations.NotNull()
        com.coinny.storedcard.data.local.entity.Transaction newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.coinny.storedcard.data.local.entity.Transaction oldItem, @org.jetbrains.annotations.NotNull()
        com.coinny.storedcard.data.local.entity.Transaction newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/coinny/storedcard/ui/carddetail/TransactionAdapter$TransactionViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/coinny/storedcard/databinding/ItemTransactionBinding;", "(Lcom/coinny/storedcard/databinding/ItemTransactionBinding;)V", "bind", "", "transaction", "Lcom/coinny/storedcard/data/local/entity/Transaction;", "app_release"})
    public static final class TransactionViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.coinny.storedcard.databinding.ItemTransactionBinding binding = null;
        
        public TransactionViewHolder(@org.jetbrains.annotations.NotNull()
        com.coinny.storedcard.databinding.ItemTransactionBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.coinny.storedcard.data.local.entity.Transaction transaction) {
        }
    }
}