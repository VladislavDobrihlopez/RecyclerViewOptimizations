package com.example.fakevkhub.presentation.community_manager_screen.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.CommunityOptionInputInfoBinding
import com.example.fakevkhub.presentation.community_manager_screen.BaseViewHolder
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.InputAdvice
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.ListedItem

class InputInfoAdapterDelegate : AdapterDelegate<CommunityOptionInputInfoBinding, InputAdvice> {
    private val diffCallback = object : DiffUtil.ItemCallback<InputAdvice>() {
        override fun areItemsTheSame(oldItem: InputAdvice, newItem: InputAdvice): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: InputAdvice, newItem: InputAdvice): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: InputAdvice, newItem: InputAdvice): Any? {
            return if (oldItem.id == newItem.id) true else null
        }
    }

    override fun isOfNeededType(item: ListedItem): Boolean {
        return item is InputAdvice
    }

    override fun getViewType(): Int {
        return R.layout.community_option_input_info
    }

    override fun createViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<CommunityOptionInputInfoBinding, InputAdvice> {
        val layout = CommunityOptionInputInfoBinding.inflate(layoutInflater, parent, false)
        return InputInfoViewHolder(layout)
    }

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<InputAdvice> {
        return diffCallback
    }

    class InputInfoViewHolder(binding: CommunityOptionInputInfoBinding) :
        BaseViewHolder<CommunityOptionInputInfoBinding, InputAdvice>(binding) {
        override fun onBind(item: InputAdvice) {
            with(binding) {
                textInputLayoutOption.hint = item.hintText
            }
        }
    }
}