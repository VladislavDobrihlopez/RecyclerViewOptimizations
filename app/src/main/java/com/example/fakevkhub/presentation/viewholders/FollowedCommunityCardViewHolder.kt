package com.example.fakevkhub.presentation.viewholders

import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.CommunityItemBinding
import com.example.fakevkhub.presentation.uimodels.CommunityUiModel

class FollowedCommunityCardViewHolder(
    private val binding: CommunityItemBinding,
    private val onEvent: (CommunityUiModel) -> Unit
) :
    BaseViewHolder<CommunityItemBinding, CommunityUiModel>(binding) {
    override fun onBind(item: CommunityUiModel) {
        with(binding) {
            imageViewStar.setOnClickListener {
                onEvent(item)
            }
            textViewName.text = item.name
            textViewSphere.text = item.sphere
            imageViewCommunityThumb.setImageResource(R.drawable.default_logo)
            imageViewStar.setImageResource(item.drawableResId)
        }
    }
}