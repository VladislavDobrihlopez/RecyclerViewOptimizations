package com.example.fakevkhub.presentation.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.CommunityItemBinding
import com.example.fakevkhub.domain.Community
import com.example.fakevkhub.presentation.uimodels.CommunityUiModel
import com.example.fakevkhub.presentation.uimodels.Item
import com.example.fakevkhub.presentation.viewholders.BaseViewHolder
import com.example.fakevkhub.presentation.viewholders.FollowedCommunityCardViewHolder

class FollowedCommunitiesDelegateAdapter(
    private val onEvent: (CommunityUiModel) -> Unit
) : AdapterDelegate<CommunityItemBinding, CommunityUiModel>() {
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
}