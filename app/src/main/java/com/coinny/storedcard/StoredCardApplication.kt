package com.coinny.storedcard

import android.app.Application
import androidx.work.*
import com.coinny.storedcard.util.NotificationUtil
import com.coinny.storedcard.worker.ExpiryCheckWorker

class StoredCardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // 创建通知渠道
        NotificationUtil.createNotificationChannel(this)

        // 简化逻辑：每次打开应用时，立即执行一次扣费和状态检查
        triggerDeductionCheck()
    }

    private fun triggerDeductionCheck() {
        // 使用 OneTimeWorkRequest 确保在进入主界面时异步完成计算，不阻塞 UI
        val checkRequest = OneTimeWorkRequestBuilder<ExpiryCheckWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
            
        WorkManager.getInstance(this).enqueueUniqueWork(
            "AppOpenExpiryCheck",
            ExistingWorkPolicy.REPLACE,
            checkRequest
        )
    }
}
