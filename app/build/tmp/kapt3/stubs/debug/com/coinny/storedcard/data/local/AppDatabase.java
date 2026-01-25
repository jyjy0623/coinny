package com.coinny.storedcard.data.local;

/**
 * 应用数据库
 *
 * 数据库版本管理原则：
 * 1. 【重要】版本升级时必须保留用户数据，禁止使用 fallbackToDestructiveMigration()
 * 2. 每次数据库结构变更必须增加版本号
 * 3. 必须提供从旧版本到新版本的Migration
 * 4. Migration中只能使用SQL语句，不能使用Entity类
 * 5. 新增字段必须设置默认值或允许NULL
 * 6. 测试Migration确保数据完整性
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\b"}, d2 = {"Lcom/coinny/storedcard/data/local/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "cardDao", "Lcom/coinny/storedcard/data/local/dao/CardDao;", "transactionDao", "Lcom/coinny/storedcard/data/local/dao/TransactionDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.coinny.storedcard.data.local.entity.Card.class, com.coinny.storedcard.data.local.entity.Transaction.class}, version = 2, exportSchema = false)
@androidx.room.TypeConverters(value = {com.coinny.storedcard.data.local.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.coinny.storedcard.data.local.AppDatabase INSTANCE;
    
    /**
     * 数据库迁移：Version 1 -> Version 2
     * 变更内容：为Card表添加daily_rate字段（天数卡每天收费金额）
     */
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_1_2 = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.coinny.storedcard.data.local.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.coinny.storedcard.data.local.dao.CardDao cardDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.coinny.storedcard.data.local.dao.TransactionDao transactionDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/coinny/storedcard/data/local/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/coinny/storedcard/data/local/AppDatabase;", "MIGRATION_1_2", "Landroidx/room/migration/Migration;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.coinny.storedcard.data.local.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}