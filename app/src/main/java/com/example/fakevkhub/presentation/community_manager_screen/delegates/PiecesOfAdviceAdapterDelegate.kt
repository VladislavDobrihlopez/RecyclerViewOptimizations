package com.example.fakevkhub.presentation.community_manager_screen.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.viewbinding.ViewBinding
import com.example.fakevkhub.R
import com.example.fakevkhub.databinding.CommunityOptionCarouselBinding
import com.example.fakevkhub.presentation.community_manager_screen.BaseViewHolder
import com.example.fakevkhub.presentation.community_manager_screen.CommunityManagerAdapter
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.ListedItem
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.SomePiecesOfAdvice

class PiecesOfAdviceAdapterDelegate(
    private val innerDelegate: List<AdapterDelegate<ViewBinding, ListedItem>>,
    private val decorator: List<ItemDecoration>
) :
    AdapterDelegate<CommunityOptionCarouselBinding, SomePiecesOfAdvice> {
    private val diffCallback = object : DiffUtil.ItemCallback<SomePiecesOfAdvice>() {
        override fun areItemsTheSame(
            oldItem: SomePiecesOfAdvice,
            newItem: SomePiecesOfAdvice
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SomePiecesOfAdvice,
            newItem: SomePiecesOfAdvice
        ): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(
            oldItem: SomePiecesOfAdvice,
            newItem: SomePiecesOfAdvice
        ): Any? {
            return (oldItem.items != newItem.items)
        }
    }

    override fun isOfNeededType(item: ListedItem): Boolean {
        return item is SomePiecesOfAdvice
    }

    override fun getViewType(): Int {
        return R.layout.community_option_carousel
    }

    override fun createViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<CommunityOptionCarouselBinding, SomePiecesOfAdvice> {
        val binding = CommunityOptionCarouselBinding.inflate(layoutInflater, parent, false)
        return PiecesOfAdviceViewHolder(decorator, binding, innerDelegate)
    }

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<SomePiecesOfAdvice> {
        return diffCallback
    }

    class PiecesOfAdviceViewHolder(
        private val decorator: List<ItemDecoration>,
        binding: CommunityOptionCarouselBinding,
        adapterDelegate: List<AdapterDelegate<ViewBinding, ListedItem>>
    ) : BaseViewHolder<CommunityOptionCarouselBinding, SomePiecesOfAdvice>(binding) {
        private val adapter = CommunityManagerAdapter(adapterDelegates = adapterDelegate)
        override fun onBind(item: SomePiecesOfAdvice) {
            //val manager = LinearLayoutManager(binding.root.context, HORIZONTAL, false)
            //binding.recyclerViewCarousel.layoutManager = manager
            binding.recyclerViewCarousel.adapter = adapter
            decorator.forEach {
                binding.recyclerViewCarousel.addItemDecoration(it)
            }
            adapter.submitList(item.items)
        }

        override fun onBind(item: SomePiecesOfAdvice, payload: List<Any>) {
            if (payload.isNotEmpty() && payload.last() == true) {
                adapter.submitList(item.items)
            }
        }
    }
}