package com.example.fakevkhub.presentation.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.CommunityItemBinding
import com.example.fakevkhub.presentation.uimodels.CommunityUiModel
import com.example.fakevkhub.presentation.uimodels.Item
import com.example.fakevkhub.presentation.viewholders.BaseViewHolder
import com.example.fakevkhub.presentation.viewholders.FollowedCommunityCardViewHolder

class FollowedCommunitiesDelegateAdapter(
    private val onEvent: (CommunityUiModel) -> Unit
) : AdapterDelegate<CommunityItemBinding, CommunityUiModel>() {
    private val diffUtilItem by lazy {
        object : DiffUtil.ItemCallback<CommunityUiModel>() {
            override fun areItemsTheSame(
                oldItem: CommunityUiModel,
                newItem: CommunityUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CommunityUiModel,
                newItem: CommunityUiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun isOfNeededType(itemForChecking: Item): Boolean {
        return itemForChecking is CommunityUiModel
    }

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<CommunityItemBinding, CommunityUiModel> {
        val binding = CommunityItemBinding.inflate(
            inflater,
            parent,
            false
        )

        return FollowedCommunityCardViewHolder(binding, onEvent)
    }

    override fun getLayoutIdAsViewType(): Int {
        return R.layout.community_item
    }

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<CommunityUiModel> {
        return diffUtilItem;
    }
}