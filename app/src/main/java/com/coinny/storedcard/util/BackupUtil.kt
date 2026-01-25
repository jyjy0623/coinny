package com.coinny.storedcard.util

import android.content.Context
import com.coinny.storedcard.data.local.entity.Card
import com.coinny.storedcard.data.local.entity.Transaction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileReader
import java.io.FileWriter

object BackupUtil {
    private const val BACKUP_FILE_NAME = "stored_card_backup.json"
    private const val CSV_FILE_NAME = "stored_card_export.csv"

    data class BackupData(
        val cards: List<Card>,
        val transactions: List<Transaction>,
        val backupTime: Long = System.currentTimeMillis()
    )

    fun exportToJson(context: Context, cards: List<Card>, transactions: List<Transaction>): File? {
        return try {
            val backupData = BackupData(cards, transactions)
            val gson = Gson()
            val json = gson.toJson(backupData)

            val file = File(context.getExternalFilesDir(null), BACKUP_FILE_NAME)
            FileWriter(file).use { it.write(json) }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun importFromJson(file: File): BackupData? {
        return try {
            val gson = Gson()
            val reader = FileReader(file)
            val type = object : TypeToken<BackupData>() {}.type
            gson.fromJson(reader, type)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun exportToCsv(context: Context, cards: List<Card>, transactions: List<Transaction>): File? {
        return try {
            val file = File(context.getExternalFilesDir(null), CSV_FILE_NAME)
            FileWriter(file).use { writer ->
                // 写入卡片数据
                writer.append("=== 储值卡列表 ===\n")
                writer.append("ID,名称,类型,初始值,当前值,过期时间,状态,创建时间\n")
                cards.forEach { card ->
                    writer.append("${card.id},")
                    writer.append("${card.name},")
                    writer.append("${card.type},")
                    writer.append("${card.initialValue},")
                    writer.append("${card.currentValue},")
                    writer.append("${card.expiryDate ?: ""},")
                    writer.append("${card.status},")
                    writer.append("${DateUtil.formatDateTime(card.createdAt)}\n")
                }

                // 写入交易记录
                writer.append("\n=== 交易记录 ===\n")
                writer.append("ID,卡片ID,类型,金额,备注,时间\n")
                transactions.forEach { transaction ->
                    writer.append("${transaction.id},")
                    writer.append("${transaction.cardId},")
                    writer.append("${transaction.type},")
                    writer.append("${transaction.amount},")
                    writer.append("${transaction.note ?: ""},")
                    writer.append("${DateUtil.formatDateTime(transaction.timestamp)}\n")
                }
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
