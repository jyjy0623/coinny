package com.coinny.storedcard.ui.carddetail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.coinny.storedcard.R
import com.coinny.storedcard.databinding.ActivityCardDetailBinding
import com.coinny.storedcard.domain.model.CardStatus
import com.coinny.storedcard.domain.model.CardType
import com.coinny.storedcard.ui.addedit.AddEditCardActivity
import com.coinny.storedcard.ui.deduct.DeductDialogFragment
import com.coinny.storedcard.util.DateUtil
import kotlinx.coroutines.launch

class CardDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCardDetailBinding
    private val viewModel: CardDetailViewModel by viewModels { CardDetailViewModel.Factory(this) }
    private lateinit var transactionAdapter: TransactionAdapter
    private var cardId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cardId = intent.getLongExtra("CARD_ID", -1)
        if (cardId == -1L) {
            Toast.makeText(this, "卡片不存在", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupRecyclerView()
        setupListeners()
        observeCard()
        observeTransactions()

        viewModel.loadCard(cardId)
    }

    private fun setupRecyclerView() {
        transactionAdapter = TransactionAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CardDetailActivity)
            adapter = transactionAdapter
        }
    }

    private fun setupListeners() {
        binding.btnRecharge.setOnClickListener {
            showDeductDialog(isRecharge = true)
        }

        binding.btnDeduct.setOnClickListener {
            showDeductDialog(isRecharge = false)
        }

        binding.btnPauseResume.setOnClickListener {
            lifecycleScope.launch {
                viewModel.togglePauseResume()
            }
        }
    }

    private fun observeCard() {
        lifecycleScope.launch {
            viewModel.card.collect { card ->
                card?.let {
                    supportActionBar?.title = it.name

                    binding.tvCardName.text = it.name

                    val typeText = when (it.type) {
                        CardType.AMOUNT -> getString(R.string.card_type_amount)
                        CardType.COUNT -> getString(R.string.card_type_count)
                        CardType.DAILY -> getString(R.string.card_type_daily)
                    }
                    binding.tvCardType.text = typeText

                    val valueText = when (it.type) {
                        CardType.AMOUNT -> "¥%.2f".format(it.currentValue)
                        CardType.COUNT -> "%.0f次".format(it.currentValue)
                        CardType.DAILY -> "¥%.2f".format(it.currentValue)
                    }
                    binding.tvCurrentValue.text = valueText

                    val initialText = when (it.type) {
                        CardType.AMOUNT -> "初始：¥%.2f".format(it.initialValue)
                        CardType.COUNT -> "初始：%.0f次".format(it.initialValue)
                        CardType.DAILY -> {
                            val dailyRateText = if (it.dailyRate != null) {
                                " (每天¥%.2f)".format(it.dailyRate)
                            } else {
                                ""
                            }
                            "初始：¥%.2f%s".format(it.initialValue, dailyRateText)
                        }
                    }
                    binding.tvInitialValue.text = initialText

                    if (it.expiryDate != null) {
                        binding.tvExpiryDate.text = "过期时间：${DateUtil.formatDate(it.expiryDate)}"
                    } else {
                        binding.tvExpiryDate.text = "无期限"
                    }

                    val statusText = when (it.status) {
                        CardStatus.ACTIVE -> getString(R.string.status_active)
                        CardStatus.PAUSED -> getString(R.string.status_paused)
                        CardStatus.EXPIRED -> getString(R.string.status_expired)
                    }
                    binding.tvStatus.text = "状态：$statusText"

                    // 只有按天卡才显示暂停/启用按钮
                    if (it.type == CardType.DAILY && it.status != CardStatus.EXPIRED) {
                        binding.btnPauseResume.visibility = View.VISIBLE
                        binding.btnPauseResume.text = if (it.status == CardStatus.PAUSED) {
                            getString(R.string.resume)
                        } else {
                            getString(R.string.pause)
                        }
                    } else {
                        binding.btnPauseResume.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun observeTransactions() {
        lifecycleScope.launch {
            viewModel.transactions.collect { transactions ->
                if (transactions.isEmpty()) {
                    binding.emptyView.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                } else {
                    binding.emptyView.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    transactionAdapter.submitList(transactions)
                }
            }
        }
    }

    private fun showDeductDialog(isRecharge: Boolean) {
        val dialog = DeductDialogFragment.newInstance(cardId, isRecharge)
        dialog.show(supportFragmentManager, "DeductDialog")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_card_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                val intent = Intent(this, AddEditCardActivity::class.java).apply {
                    putExtra("CARD_ID", cardId)
                }
                startActivity(intent)
                true
            }
            R.id.action_delete -> {
                showDeleteConfirmDialog()
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteConfirmDialog() {
        AlertDialog.Builder(this)
            .setTitle("删除卡片")
            .setMessage("确定要删除这张卡片吗？此操作不可撤销，且会删除相关的交易记录。")
            .setPositiveButton("删除") { _, _ ->
                lifecycleScope.launch {
                    viewModel.deleteCard()
                    Toast.makeText(this@CardDetailActivity, "卡片已删除", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
