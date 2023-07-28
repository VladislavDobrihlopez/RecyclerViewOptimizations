package com.example.fakevkhub.presentation.adapters.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.SectionBinding
import com.example.fakevkhub.presentation.adapters.viewholders.BaseViewHolder
import com.example.fakevkhub.presentation.adapters.viewholders.SectionNameViewHolder
import com.example.fakevkhub.presentation.uimodels.Item
import com.example.fakevkhub.presentation.uimodels.SectionInfo

class SectionNameDelegateAdapter: AdapterDelegate<SectionBinding, SectionInfo>() {
    private val diffUtil by lazy {
        object: DiffUtil.ItemCallback<SectionInfo>() {
            override fun areItemsTheSame(oldItem: SectionInfo, newItem: SectionInfo): Boolean {
                return true
            }

            override fun areContentsTheSame(oldItem: SectionInfo, newItem: SectionInfo): Boolean {
                return true
            }
        }
    }
    override fun isOfNeededType(itemForChecking: Item): Boolean {
        return itemForChecking is SectionInfo
    }

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<SectionBinding, SectionInfo> {
        val binding = SectionBinding.inflate(inflater, parent, false)
        return SectionNameViewHolder(binding)
    }

    override fun getLayoutIdAsViewType(): Int {
        return R.layout.section
    }

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<SectionInfo> {
        return diffUtil
    }
}