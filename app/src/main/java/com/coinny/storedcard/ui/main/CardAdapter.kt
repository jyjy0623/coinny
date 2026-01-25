package com.coinny.storedcard.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coinny.storedcard.R
import com.coinny.storedcard.data.local.entity.Card
import com.coinny.storedcard.databinding.ItemCardBinding
import com.coinny.storedcard.domain.model.CardStatus
import com.coinny.storedcard.domain.model.CardType
import com.coinny.storedcard.util.DateUtil

class CardAdapter(
    private val onCardClick: (Card) -> Unit
) : ListAdapter<Card, CardAdapter.CardViewHolder>(CardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardViewHolder(
        private val binding: ItemCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            binding.apply {
                tvCardName.text = card.name

                // 设置卡片类型
                val typeText = when (card.type) {
                    CardType.AMOUNT -> root.context.getString(R.string.card_type_amount)
                    CardType.COUNT -> root.context.getString(R.string.card_type_count)
                    CardType.DAILY -> root.context.getString(R.string.card_type_daily)
                }
                tvCardType.text = typeText

                // 设置类型背景颜色
                val typeColor = when (card.type) {
                    CardType.AMOUNT -> ContextCompat.getColor(root.context, R.color.card_amount)
                    CardType.COUNT -> ContextCompat.getColor(root.context, R.color.card_count)
                    CardType.DAILY -> ContextCompat.getColor(root.context, R.color.card_daily)
                }
                tvCardType.setBackgroundColor(typeColor)

                // 设置当前值
                val valueText = when (card.type) {
                    CardType.AMOUNT -> "¥%.2f".format(card.currentValue)
                    CardType.COUNT -> "%.0f次".format(card.currentValue)
                    CardType.DAILY -> {
                        val dailyRateText = if (card.dailyRate != null) {
                            " (¥%.2f/天)".format(card.dailyRate)
                        } else {
                            ""
                        }
                        "¥%.2f%s".format(card.currentValue, dailyRateText)
                    }
                }
                tvCurrentValue.text = valueText

                // 设置状态
                val statusText = when (card.status) {
                    CardStatus.ACTIVE -> root.context.getString(R.string.status_active)
                    CardStatus.PAUSED -> root.context.getString(R.string.status_paused)
                    CardStatus.EXPIRED -> root.context.getString(R.string.status_expired)
                }
                tvStatus.text = statusText

                val statusColor = when (card.status) {
                    CardStatus.ACTIVE -> ContextCompat.getColor(root.context, R.color.status_active)
                    CardStatus.PAUSED -> ContextCompat.getColor(root.context, R.color.status_paused)
                    CardStatus.EXPIRED -> ContextCompat.getColor(root.context, R.color.status_expired)
                }
                tvStatus.setTextColor(statusColor)

                // 设置过期时间
                if (card.expiryDate != null) {
                    tvExpiryDate.text = "过期时间：${DateUtil.formatDate(card.expiryDate)}"
                } else {
                    tvExpiryDate.text = "无期限"
                }

                root.setOnClickListener { onCardClick(card) }
            }
        }
    }

    class CardDiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }
}
