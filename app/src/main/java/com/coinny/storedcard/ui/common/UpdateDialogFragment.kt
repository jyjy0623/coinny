package com.coinny.storedcard.ui.common

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.coinny.storedcard.domain.model.VersionInfo
import com.coinny.storedcard.util.UpdateUtil

class UpdateDialogFragment : DialogFragment() {

    private var versionInfo: VersionInfo? = null
    private var downloadReceiver: BroadcastReceiver? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        versionInfo = arguments?.getSerializable(ARG_VERSION_INFO) as? VersionInfo

        val message = buildString {
            append("发现新版本：${versionInfo?.versionName}\n\n")
            append("更新内容：\n")
            append(versionInfo?.updateMessage ?: "")
        }

        val builder = AlertDialog.Builder(requireContext())
            .setTitle("版本更新")
            .setMessage(message)
            .setPositiveButton("立即更新") { _, _ ->
                startDownload()
            }

        // 如果不是强制更新，显示取消按钮
        if (versionInfo?.forceUpdate != true) {
            builder.setNegativeButton("稍后更新", null)
        }

        isCancelable = versionInfo?.forceUpdate != true

        return builder.create()
    }

    private fun startDownload() {
        val url = versionInfo?.downloadUrl ?: return
        val versionName = versionInfo?.versionName ?: return

        Toast.makeText(requireContext(), "开始下载更新...", Toast.LENGTH_SHORT).show()

        // 注册下载完成监听
        downloadReceiver = UpdateUtil.registerDownloadReceiver(requireContext()) { file ->
            Toast.makeText(requireContext(), "下载完成，准备安装", Toast.LENGTH_SHORT).show()
            UpdateUtil.installApk(requireContext(), file)
        }

        // 开始下载
        UpdateUtil.downloadApk(requireContext(), url, versionName)
    }

    override fun onDestroy() {
        super.onDestroy()
        downloadReceiver?.let {
            try {
                requireContext().unregisterReceiver(it)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val ARG_VERSION_INFO = "version_info"

        fun newInstance(versionInfo: VersionInfo): UpdateDialogFragment {
            return UpdateDialogFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_VERSION_INFO, versionInfo)
                }
            }
        }
    }
}
