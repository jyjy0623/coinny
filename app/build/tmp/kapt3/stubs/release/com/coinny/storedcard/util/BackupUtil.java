package com.coinny.storedcard.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J,\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bJ,\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/coinny/storedcard/util/BackupUtil;", "", "()V", "BACKUP_FILE_NAME", "", "CSV_FILE_NAME", "exportToCsv", "Ljava/io/File;", "context", "Landroid/content/Context;", "cards", "", "Lcom/coinny/storedcard/data/local/entity/Card;", "transactions", "Lcom/coinny/storedcard/data/local/entity/Transaction;", "exportToJson", "importFromJson", "Lcom/coinny/storedcard/util/BackupUtil$BackupData;", "file", "BackupData", "app_release"})
public final class BackupUtil {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BACKUP_FILE_NAME = "stored_card_backup.json";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CSV_FILE_NAME = "stored_card_export.csv";
    @org.jetbrains.annotations.NotNull()
    public static final com.coinny.storedcard.util.BackupUtil INSTANCE = null;
    
    private BackupUtil() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.File exportToJson(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.coinny.storedcard.data.local.entity.Card> cards, @org.jetbrains.annotations.NotNull()
    java.util.List<com.coinny.storedcard.data.local.entity.Transaction> transactions) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.coinny.storedcard.util.BackupUtil.BackupData importFromJson(@org.jetbrains.annotations.NotNull()
    java.io.File file) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.File exportToCsv(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.coinny.storedcard.data.local.entity.Card> cards, @org.jetbrains.annotations.NotNull()
    java.util.List<com.coinny.storedcard.data.local.entity.Transaction> transactions) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\bH\u00c6\u0003J3\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r\u00a8\u0006\u001a"}, d2 = {"Lcom/coinny/storedcard/util/BackupUtil$BackupData;", "", "cards", "", "Lcom/coinny/storedcard/data/local/entity/Card;", "transactions", "Lcom/coinny/storedcard/data/local/entity/Transaction;", "backupTime", "", "(Ljava/util/List;Ljava/util/List;J)V", "getBackupTime", "()J", "getCards", "()Ljava/util/List;", "getTransactions", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"})
    public static final class BackupData {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.coinny.storedcard.data.local.entity.Card> cards = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.coinny.storedcard.data.local.entity.Transaction> transactions = null;
        private final long backupTime = 0L;
        
        public BackupData(@org.jetbrains.annotations.NotNull()
        java.util.List<com.coinny.storedcard.data.local.entity.Card> cards, @org.jetbrains.annotations.NotNull()
        java.util.List<com.coinny.storedcard.data.local.entity.Transaction> transactions, long backupTime) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.coinny.storedcard.data.local.entity.Card> getCards() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.coinny.storedcard.data.local.entity.Transaction> getTransactions() {
            return null;
        }
        
        public final long getBackupTime() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.coinny.storedcard.data.local.entity.Card> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.coinny.storedcard.data.local.entity.Transaction> component2() {
            return null;
        }
        
        public final long component3() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.coinny.storedcard.util.BackupUtil.BackupData copy(@org.jetbrains.annotations.NotNull()
        java.util.List<com.coinny.storedcard.data.local.entity.Card> cards, @org.jetbrains.annotations.NotNull()
        java.util.List<com.coinny.storedcard.data.local.entity.Transaction> transactions, long backupTime) {
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