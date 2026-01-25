package com.coinny.storedcard.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.coinny.storedcard.data.local.dao.CardDao
import com.coinny.storedcard.data.local.dao.TransactionDao
import com.coinny.storedcard.data.local.entity.Card
import com.coinny.storedcard.data.local.entity.Transaction

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
@Database(
    entities = [Card::class, Transaction::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * 数据库迁移：Version 1 -> Version 2
         * 变更内容：为Card表添加daily_rate字段（天数卡每天收费金额）
         */
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // 添加daily_rate列，默认为NULL（仅天数卡使用）
                database.execSQL(
                    "ALTER TABLE card ADD COLUMN daily_rate REAL DEFAULT NULL"
                )
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "stored_card_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
