package com.example.fakevkhub.presentation.adapters.viewholders

import android.util.Log
import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.CommunityItemBinding
import com.example.fakevkhub.presentation.adapters.PayloadChange
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

    override fun onBind(item: CommunityUiModel, payloads: List<Any>) {
        Log.d("TEST_PAYLOAD", payloads.size.toString())

        val changes = PayloadChange.createTwoEndPayload<FollowedCommunitiesPayloads>(changes = payloads)

        if (changes.first == changes.second && payloads.size > 1) {
            return
        }

        when (val change = changes.second) {
            is FollowedCommunitiesPayloads.Like -> {
                val likeState = change.isLiked
                binding.imageViewStar.setImageResource(if (likeState) R.drawable.star else R.drawable.no_star)
            }
        }

        // Иначе останется слушатель клика со старой моделькой, где лайка нет
        binding.imageViewStar.setOnClickListener {
            onEvent(item)
        }
    }
}

sealed class FollowedCommunitiesPayloads {
    class Like(val isLiked: Boolean) : FollowedCommunitiesPayloads()
}