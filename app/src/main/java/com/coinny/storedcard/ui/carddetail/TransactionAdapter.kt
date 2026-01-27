package com.coinny.storedcard.ui.carddetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coinny.storedcard.R
import com.coinny.storedcard.data.local.entity.Transaction
import com.coinny.storedcard.databinding.ItemTransactionBinding
import com.coinny.storedcard.domain.model.TransactionType
import com.coinny.storedcard.util.DateUtil

class TransactionAdapter(
    private val onLongClick: (Transaction) -> Unit
) : ListAdapter<Transaction, TransactionAdapter.TransactionViewHolder>(TransactionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding, onLongClick)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TransactionViewHolder(
        private val binding: ItemTransactionBinding,
        private val onLongClick: (Transaction) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction) {
            binding.apply {
                root.setOnLongClickListener {
                    onLongClick(transaction)
                    true
                }

                val typeText = when (transaction.type) {
                    TransactionType.CREATE -> root.context.getString(R.string.transaction_create)
                    TransactionType.RECHARGE -> root.context.getString(R.string.transaction_recharge)
                    TransactionType.DEDUCT -> root.context.getString(R.string.transaction_deduct)
                }
                tvType.text = typeText

                val amountText = when (transaction.type) {
                    TransactionType.RECHARGE -> "+%.2f".format(transaction.amount)
                    TransactionType.DEDUCT -> "-%.2f".format(transaction.amount)
                    TransactionType.CREATE -> "%.2f".format(transaction.amount)
                }
                tvAmount.text = amountText

                val amountColor = when (transaction.type) {
                    TransactionType.RECHARGE -> ContextCompat.getColor(root.context, R.color.transaction_positive)
                    TransactionType.DEDUCT -> ContextCompat.getColor(root.context, R.color.transaction_negative)
                    TransactionType.CREATE -> ContextCompat.getColor(root.context, R.color.text_primary)
                }
                tvAmount.setTextColor(amountColor)

                if (transaction.note.isNullOrEmpty()) {
                    tvNote.visibility = View.GONE
                } else {
                    tvNote.visibility = View.VISIBLE
                    tvNote.text = transaction.note
                }

                tvTimestamp.text = DateUtil.formatDateTime(transaction.timestamp)
            }
        }
    }

    class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem == newItem
        }
    }
}
