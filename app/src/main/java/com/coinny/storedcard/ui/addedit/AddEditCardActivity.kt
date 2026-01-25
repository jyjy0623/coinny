package com.coinny.storedcard.ui.addedit

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.coinny.storedcard.R
import com.coinny.storedcard.databinding.ActivityAddEditCardBinding
import com.coinny.storedcard.domain.model.CardType
import com.coinny.storedcard.util.DateUtil
import kotlinx.coroutines.launch
import java.util.*

class AddEditCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditCardBinding
    private val viewModel: AddEditCardViewModel by viewModels { AddEditCardViewModel.Factory(this) }
    private var selectedExpiryDate: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.add_card_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupListeners()
    }

    private fun setupListeners() {
        binding.rgCardType.setOnCheckedChangeListener { _, checkedId ->
            // 只有选择天数卡时才显示每天收费输入框
            binding.tilDailyRate.visibility = if (checkedId == R.id.rbDaily) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
        }

        binding.btnSelectDate.setOnClickListener {
            showDatePicker()
        }

        binding.btnSave.setOnClickListener {
            saveCard()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth, 23, 59, 59)
                selectedExpiryDate = calendar.timeInMillis
                binding.tvSelectedDate.text = DateUtil.formatDate(selectedExpiryDate!!)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun saveCard() {
        val name = binding.etCardName.text.toString().trim()
        if (name.isEmpty()) {
            Toast.makeText(this, "请输入卡片名称", Toast.LENGTH_SHORT).show()
            return
        }

        val cardType = when (binding.rgCardType.checkedRadioButtonId) {
            R.id.rbAmount -> CardType.AMOUNT
            R.id.rbCount -> CardType.COUNT
            R.id.rbDaily -> CardType.DAILY
            else -> {
                Toast.makeText(this, "请选择卡片类型", Toast.LENGTH_SHORT).show()
                return
            }
        }

        val valueString = binding.etInitialValue.text.toString().trim()
        if (valueString.isEmpty()) {
            Toast.makeText(this, "请输入初始值", Toast.LENGTH_SHORT).show()
            return
        }

        val initialValue = valueString.toDoubleOrNull()
        if (initialValue == null || initialValue <= 0) {
            Toast.makeText(this, "请输入有效的数值", Toast.LENGTH_SHORT).show()
            return
        }

        // 如果是天数卡，需要验证每天收费金额
        var dailyRate: Double? = null
        if (cardType == CardType.DAILY) {
            val dailyRateString = binding.etDailyRate.text.toString().trim()
            if (dailyRateString.isEmpty()) {
                Toast.makeText(this, "请输入每天收费金额", Toast.LENGTH_SHORT).show()
                return
            }
            dailyRate = dailyRateString.toDoubleOrNull()
            if (dailyRate == null || dailyRate <= 0) {
                Toast.makeText(this, "请输入有效的每天收费金额", Toast.LENGTH_SHORT).show()
                return
            }
        }

        lifecycleScope.launch {
            val result = viewModel.createCard(name, cardType, initialValue, selectedExpiryDate, dailyRate)
            if (result.isSuccess) {
                Toast.makeText(this@AddEditCardActivity, "卡片创建成功", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(
                    this@AddEditCardActivity,
                    "创建失败：${result.exceptionOrNull()?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
