package com.coinny.storedcard

import android.app.Application
import androidx.work.*
import com.coinny.storedcard.util.NotificationUtil
import com.coinny.storedcard.worker.ExpiryCheckWorker
import java.util.concurrent.TimeUnit

class StoredCardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // 创建通知渠道
        NotificationUtil.createNotificationChannel(this)

        // 1. 设置周期性任务（改为 15 分钟检查一次，这是系统允许的最小间隔）
        setupPeriodicExpiryCheck()
        
        // 2. 立即触发一次单次任务
        triggerImmediateCheck()
    }

    private fun setupPeriodicExpiryCheck() {
        // 注意：WorkManager 强制最小间隔为 15 分钟
        val workRequest = PeriodicWorkRequestBuilder<ExpiryCheckWorker>(
            15, TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "ExpiryCheckWorker",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    private fun triggerImmediateCheck() {
        val immediateRequest = OneTimeWorkRequestBuilder<ExpiryCheckWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
            
        WorkManager.getInstance(this).enqueueUniqueWork(
            "ImmediateExpiryCheck",
            ExistingWorkPolicy.REPLACE,
            immediateRequest
        )
    }
}
