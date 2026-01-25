package com.coinny.storedcard.util

import android.content.Context
import com.coinny.storedcard.domain.model.VersionInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

object VersionCheckUtil {

    // 版本检查URL - 您需要将此URL替换为您的服务器地址
    private const val VERSION_CHECK_URL = "https://jyjy0623.github.io/coinny/version.json"

    /**
     * 检查是否有新版本
     * 返回null表示没有新版本或检查失败
     */
    suspend fun checkForUpdate(context: Context): VersionInfo? = withContext(Dispatchers.IO) {
        try {
            // 从服务器获取版本信息
            val response = URL(VERSION_CHECK_URL).readText()
            val json = JSONObject(response)

            val latestVersionCode = json.getInt("versionCode")
            val latestVersionName = json.getString("versionName")
            val downloadUrl = json.getString("downloadUrl")
            val updateMessage = json.getString("updateMessage")
            val forceUpdate = json.optBoolean("forceUpdate", false)

            val currentVersionCode = UpdateUtil.getVersionCode(context)

            // 如果服务器版本号大于当前版本号，返回更新信息
            if (latestVersionCode > currentVersionCode) {
                VersionInfo(
                    versionCode = latestVersionCode,
                    versionName = latestVersionName,
                    downloadUrl = downloadUrl,
                    updateMessage = updateMessage,
                    forceUpdate = forceUpdate
                )
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 模拟版本检查（用于测试）
     * 实际使用时应该删除此方法，使用上面的checkForUpdate
     */
    suspend fun checkForUpdateMock(context: Context): VersionInfo? = withContext(Dispatchers.IO) {
        try {
            val currentVersionCode = UpdateUtil.getVersionCode(context)

            // 模拟服务器返回的版本信息（版本号比当前大1）
            VersionInfo(
                versionCode = currentVersionCode + 1,
                versionName = "1.1.0",
                downloadUrl = "https://example.com/app-release.apk",
                updateMessage = "1. 修复了深色主题显示问题\n2. 优化了界面体验\n3. 修复了已知bug",
                forceUpdate = false
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
