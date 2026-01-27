package com.coinny.storedcard

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.coinny.storedcard.util.NotificationUtil
import com.coinny.storedcard.worker.ExpiryCheckWorker
import java.util.concurrent.TimeUnit

class StoredCardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // 创建通知渠道
        NotificationUtil.createNotificationChannel(this)

        // 设置定期检查任务
        setupExpiryCheckWorker()
    }

    private fun setupExpiryCheckWorker() {
        val workRequest = PeriodicWorkRequestBuilder<ExpiryCheckWorker>(
            1, TimeUnit.DAYS
        ).build()

        // 修改点：使用 UPDATE 确保新版本安装后任务逻辑被刷新
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "ExpiryCheckWorker",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}
