package com.coinny.storedcard.ui.addedit

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
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
    private var cardId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cardId = intent.getLongExtra("CARD_ID", -1)
        
        if (cardId != -1L) {
            supportActionBar?.title = getString(R.string.edit_card_title)
            viewModel.loadCard(cardId)
        } else {
            supportActionBar?.title = getString(R.string.add_card_title)
        }
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.rgCardType.setOnCheckedChangeListener { _, checkedId ->
            binding.tilDailyRate.visibility = if (checkedId == R.id.rbDaily) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        binding.btnSelectDate.setOnClickListener {
            showDatePicker()
        }

        binding.btnSave.setOnClickListener {
            saveCard()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.card.collect { card ->
                card?.let {
                    binding.etCardName.setText(it.name)
                    // 编辑模式下显示当前余额，并允许修改
                    binding.etInitialValue.setText(it.currentValue.toString())
                    
                    // 修改提示词，让用户知道现在这里是修改当前余额
                    val hint = when (it.type) {
                        CardType.AMOUNT -> getString(R.string.amount_hint)
                        CardType.COUNT -> getString(R.string.count_hint)
                        CardType.DAILY -> getString(R.string.amount_hint)
                    }
                    binding.tilInitialValue.hint = "当前余额/次数 ($hint)"

                    // 编辑模式下不允许修改卡片类型
                    binding.rgCardType.isEnabled = false
                    binding.rbAmount.isEnabled = false
                    binding.rbCount.isEnabled = false
                    binding.rbDaily.isEnabled = false
                    
                    when (it.type) {
                        CardType.AMOUNT -> binding.rbAmount.isChecked = true
                        CardType.COUNT -> binding.rbCount.isChecked = true
                        CardType.DAILY -> {
                            binding.rbDaily.isChecked = true
                            binding.tilDailyRate.visibility = View.VISIBLE
                            binding.etDailyRate.setText(it.dailyRate?.toString() ?: "")
                        }
                    }
                    
                    // 编辑模式下允许修改当前余额
                    binding.etInitialValue.isEnabled = true

                    if (it.expiryDate != null) {
                        selectedExpiryDate = it.expiryDate
                        binding.tvSelectedDate.text = DateUtil.formatDate(it.expiryDate!!)
                    }
                }
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        if (selectedExpiryDate != null) {
            calendar.timeInMillis = selectedExpiryDate!!
        }
        
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
            Toast.makeText(this, "请输入有效数值", Toast.LENGTH_SHORT).show()
            return
        }

        val newValue = valueString.toDoubleOrNull()
        if (newValue == null || newValue < 0) {
            Toast.makeText(this, "请输入有效的数值", Toast.LENGTH_SHORT).show()
            return
        }

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
            val result = viewModel.saveCard(name, cardType, newValue, selectedExpiryDate, dailyRate)
            if (result.isSuccess) {
                val message = if (cardId == -1L) "卡片创建成功" else "卡片更新成功"
                Toast.makeText(this@AddEditCardActivity, message, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(
                    this@AddEditCardActivity,
                    "保存失败：${result.exceptionOrNull()?.message}",
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
