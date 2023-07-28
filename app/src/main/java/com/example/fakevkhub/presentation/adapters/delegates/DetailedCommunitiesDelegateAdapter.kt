package com.example.fakevkhub.presentation.adapters.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.DetailedCommunityItemBinding
import com.example.fakevkhub.presentation.adapters.viewholders.BaseViewHolder
import com.example.fakevkhub.presentation.adapters.viewholders.DetailedCommunitiesPayloads
import com.example.fakevkhub.presentation.adapters.viewholders.DetailedCommunityCardViewHolder
import com.example.fakevkhub.presentation.uimodels.DetailedCommunityUiModel
import com.example.fakevkhub.presentation.uimodels.Item

class DetailedCommunitiesDelegateAdapter(
    private val onFollowed: (DetailedCommunityUiModel) -> Unit, // recyclerViewId and ui model
    private val itemWidth: Int? = null
) : AdapterDelegate<DetailedCommunityItemBinding, DetailedCommunityUiModel>() {
    private val diffUtilItem by lazy {
        object : DiffUtil.ItemCallback<DetailedCommunityUiModel>() {
            override fun areItemsTheSame(
                oldItem: DetailedCommunityUiModel,
                newItem: DetailedCommunityUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DetailedCommunityUiModel,
                newItem: DetailedCommunityUiModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(
                oldItem: DetailedCommunityUiModel,
                newItem: DetailedCommunityUiModel
            ): Any? {
                return if (oldItem.isFollowed != newItem.isFollowed) {
                    DetailedCommunitiesPayloads.Followed(newItem.isFollowed)
                } else {
                    super.getChangePayload(oldItem, newItem)
                }
            }
        }
    }

    override fun isOfNeededType(itemForChecking: Item): Boolean {
        return itemForChecking is DetailedCommunityUiModel
    }

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<DetailedCommunityItemBinding, DetailedCommunityUiModel> {
        val binding = DetailedCommunityItemBinding.inflate(inflater, parent, false)

        with(binding.root.layoutParams) {
            width = itemWidth ?: width
        }

        return DetailedCommunityCardViewHolder(
            onFollowed,
            binding
        )
    }

    override fun getLayoutIdAsViewType(): Int {
        return R.layout.detailed_community_item
    }

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<DetailedCommunityUiModel> {
        return diffUtilItem
    }
}