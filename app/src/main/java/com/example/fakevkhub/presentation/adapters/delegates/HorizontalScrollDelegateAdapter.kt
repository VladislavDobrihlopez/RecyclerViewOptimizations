package com.example.fakevkhub.presentation.adapters.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.CommunitiesDetailedBinding
import com.example.fakevkhub.presentation.adapters.viewholders.BaseViewHolder
import com.example.fakevkhub.presentation.adapters.viewholders.HorizontalBunchOfCommunitiesViewHolder
import com.example.fakevkhub.presentation.uimodels.CommunitiesHolder
import com.example.fakevkhub.presentation.uimodels.Item

class HorizontalScrollDelegateAdapter(
    private val adapterDelegates: List<AdapterDelegate<*, *>>
) : AdapterDelegate<CommunitiesDetailedBinding, CommunitiesHolder>() {
    private val diffUtil by lazy {
        object : DiffUtil.ItemCallback<CommunitiesHolder>() {
            override fun areItemsTheSame(
                oldItem: CommunitiesHolder,
                newItem: CommunitiesHolder
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CommunitiesHolder,
                newItem: CommunitiesHolder
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun isOfNeededType(itemForChecking: Item): Boolean {
        return itemForChecking is CommunitiesHolder
    }

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<CommunitiesDetailedBinding, CommunitiesHolder> {
        return HorizontalBunchOfCommunitiesViewHolder(
            CommunitiesDetailedBinding.inflate(inflater), adapterDelegates
        )
    }

    override fun getLayoutIdAsViewType(): Int {
        return R.layout.communities_detailed
    }

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<CommunitiesHolder> {
        return diffUtil
    }
}