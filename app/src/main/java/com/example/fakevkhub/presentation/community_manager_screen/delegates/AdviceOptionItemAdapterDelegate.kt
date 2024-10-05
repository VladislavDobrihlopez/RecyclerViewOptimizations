package com.example.fakevkhub.presentation.community_manager_screen.delegates

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.CommunityOptionAdviceItemBinding
import com.example.fakevkhub.presentation.community_manager_screen.BaseViewHolder
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.Advice
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.ListedItem

class AdviceOptionItemAdapterDelegate(
    private val onButtonClick: (item: Advice) -> Unit
) : AdapterDelegate<CommunityOptionAdviceItemBinding, Advice> {
    private val diffCallback = object : DiffUtil.ItemCallback<Advice>() {
        override fun areItemsTheSame(oldItem: Advice, newItem: Advice): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Advice, newItem: Advice): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: Advice, newItem: Advice): Any? {
            Log.d("TEST_PAYLOAD", "${oldItem.isApplied} ${newItem.isApplied}")
            return oldItem.isApplied != newItem.isApplied
        }
    }

    override fun isOfNeededType(item: ListedItem): Boolean {
        return item is Advice
    }

    override fun getViewType(): Int {
        return R.layout.community_option_advice_item
    }

    override fun createViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<CommunityOptionAdviceItemBinding, Advice> {
        val layout = CommunityOptionAdviceItemBinding.inflate(layoutInflater, parent, false)
        return AdviceOptionViewHolder(layout) {
            onButtonClick(it)
        }
    }

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<Advice> {
        return diffCallback
    }

    class AdviceOptionViewHolder(
        binding: CommunityOptionAdviceItemBinding,
        private val onButtonClick: (item: Advice) -> Unit
    ) :
        BaseViewHolder<CommunityOptionAdviceItemBinding, Advice>(binding) {
        override fun onBind(item: Advice) {
            with(binding) {
                textViewDescription.text = item.text
                buttonOkay.text = if (item.isApplied) "applied" else item.buttonText
                buttonOkay.setOnClickListener {
                    onButtonClick(item)
                }
            }
        }

        override fun onBind(item: Advice, payload: List<Any>) {
            Log.d("TEST_PAYLOAD", "onBind: $item")
            if (payload.last() is Boolean) {
                binding.buttonOkay.text = if (item.isApplied) "applied" else item.buttonText
                binding.buttonOkay.setOnClickListener {
                    onButtonClick(item)
                }
            }
        }
    }
}