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

        // 1. 彻底取消老版本遗留的后台周期性任务（防止与新逻辑冲突导致双重扣费）
        WorkManager.getInstance(this).cancelUniqueWork("ExpiryCheckWorker")

        // 2. 执行新的冷启动扣费检查
        triggerDeductionCheck()
    }

    private fun triggerDeductionCheck() {
        val checkRequest = OneTimeWorkRequestBuilder<ExpiryCheckWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
            
        // 统一使用一个唯一的任务名称，确保同一时间只有一个检查在跑
        WorkManager.getInstance(this).enqueueUniqueWork(
            "UniversalDeductionCheck",
            ExistingWorkPolicy.REPLACE,
            checkRequest
        )
    }
}
