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

        // 设置定期检查任务（每天检查一次）
        setupExpiryCheckWorker()
    }

    private fun setupExpiryCheckWorker() {
        val workRequest = PeriodicWorkRequestBuilder<ExpiryCheckWorker>(
            1, TimeUnit.DAYS
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "ExpiryCheckWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}
