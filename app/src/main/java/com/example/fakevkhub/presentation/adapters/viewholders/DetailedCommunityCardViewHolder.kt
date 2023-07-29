package com.example.fakevkhub.presentation.adapters.viewholders

import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.DetailedCommunityItemBinding
import com.example.fakevkhub.presentation.adapters.PayloadChange
import com.example.fakevkhub.presentation.uimodels.DetailedCommunityUiModel

class DetailedCommunityCardViewHolder(
    private val onFollowed: (DetailedCommunityUiModel) -> Unit,
    private val binding: DetailedCommunityItemBinding
) : BaseViewHolder<DetailedCommunityItemBinding, DetailedCommunityUiModel>(binding) {
    override fun onBind(item: DetailedCommunityUiModel) {
        with(binding) {
            textViewName.text = item.name
            textViewSphere.text = item.sphere
            textViewFollowers.text = item.followers
//            imageViewBackground.background = Color.BLACK
            imageViewGroupImage.setImageResource(item.groupImageUrl.toInt()) // for testing purpose
            imageButtonSubscribe.setOnClickListener {
                onFollowed(item)
            }
            binding.imageButtonSubscribe.setImageResource(item.buttonDrawableResId)
        }
    }

    override fun onBind(item: DetailedCommunityUiModel, payloads: List<Any>) {
        val changes =
            PayloadChange.createTwoEndPayload<DetailedCommunitiesPayloads>(changes = payloads)
        if (changes.first == changes.second && payloads.size > 1) {
            return
        }

        when (val change = changes.second) {
            is DetailedCommunitiesPayloads.Followed -> {
                binding.imageButtonSubscribe.setImageResource(
                    if (change.isFollowed) {
                        R.drawable.galka
                    } else {
                        R.drawable.dadac
                    }
                )
            }
        }

        binding.imageButtonSubscribe.setOnClickListener {
            onFollowed(item)
        }
    }
}

sealed class DetailedCommunitiesPayloads {
    class Followed(val isFollowed: Boolean) : DetailedCommunitiesPayloads()
}