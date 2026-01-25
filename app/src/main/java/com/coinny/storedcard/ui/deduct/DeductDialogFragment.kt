package com.coinny.storedcard.ui.deduct

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.coinny.storedcard.R
import com.coinny.storedcard.databinding.DialogDeductBinding
import kotlinx.coroutines.launch

class DeductDialogFragment : DialogFragment() {
    private var _binding: DialogDeductBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DeductViewModel by viewModels { DeductViewModel.Factory(requireContext()) }

    private var cardId: Long = -1
    private var isRecharge: Boolean = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogDeductBinding.inflate(layoutInflater)

        cardId = arguments?.getLong(ARG_CARD_ID) ?: -1
        isRecharge = arguments?.getBoolean(ARG_IS_RECHARGE) ?: false

        val title = if (isRecharge) {
            getString(R.string.recharge_title)
        } else {
            getString(R.string.deduct_title)
        }
        binding.tvTitle.text = title

        setupListeners()

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }

    private fun setupListeners() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnConfirm.setOnClickListener {
            processTransaction()
        }
    }

    private fun processTransaction() {
        val amountString = binding.etAmount.text.toString().trim()
        if (amountString.isEmpty()) {
            Toast.makeText(requireContext(), "请输入金额", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountString.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            Toast.makeText(requireContext(), getString(R.string.error_invalid_input), Toast.LENGTH_SHORT).show()
            return
        }

        val note = binding.etNote.text.toString().trim()

        lifecycleScope.launch {
            val result = if (isRecharge) {
                viewModel.rechargeCard(cardId, amount, note)
            } else {
                viewModel.deductCard(cardId, amount, note)
            }

            if (result.isSuccess) {
                Toast.makeText(
                    requireContext(),
                    if (isRecharge) "充值成功" else getString(R.string.success_deduct),
                    Toast.LENGTH_SHORT
                ).show()
                dismiss()
            } else {
                Toast.makeText(
                    requireContext(),
                    result.exceptionOrNull()?.message ?: "操作失败",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_CARD_ID = "card_id"
        private const val ARG_IS_RECHARGE = "is_recharge"

        fun newInstance(cardId: Long, isRecharge: Boolean): DeductDialogFragment {
            return DeductDialogFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_CARD_ID, cardId)
                    putBoolean(ARG_IS_RECHARGE, isRecharge)
                }
            }
        }
    }
}
