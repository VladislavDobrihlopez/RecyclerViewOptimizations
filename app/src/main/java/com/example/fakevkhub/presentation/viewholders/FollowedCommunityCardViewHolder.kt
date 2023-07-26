package com.example.fakevkhub.presentation.viewholders

import android.util.Log
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

    override fun onBind(item: CommunityUiModel, payloads: List<Any>) {
        payloads.forEach { payload ->
            Log.d("TEST_PAYLOAD", payload.toString())

            when (payload) {
                is FollowedCommunitiesPayloads.Like -> {
                    val likeState = payload.isLiked
                    binding.imageViewStar.setImageResource(if (likeState) R.drawable.star else R.drawable.no_star)
                }
            }

            // Иначе останется слушатель клика со старой моделькой, где лайка нет
            binding.imageViewStar.setOnClickListener {
                onEvent(item)
            }
        }
    }
}

sealed class FollowedCommunitiesPayloads {
    class Like(val isLiked: Boolean) : FollowedCommunitiesPayloads()
}