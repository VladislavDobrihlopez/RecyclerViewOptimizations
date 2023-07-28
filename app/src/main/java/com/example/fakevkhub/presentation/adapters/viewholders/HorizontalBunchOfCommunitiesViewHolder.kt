package com.example.fakevkhub.presentation.adapters.viewholders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakevkhub.databinding.CommunitiesDetailedBinding
import com.example.fakevkhub.presentation.adapters.DetailedCommunitiesAdapter
import com.example.fakevkhub.presentation.adapters.delegates.AdapterDelegate
import com.example.fakevkhub.presentation.onRestoreState
import com.example.fakevkhub.presentation.uimodels.CommunitiesHolder

class HorizontalBunchOfCommunitiesViewHolder(
    private val binding: CommunitiesDetailedBinding,
    private val adapterDelegates: List<AdapterDelegate<*, *>>,
    private val recycledViewPool: RecyclerView.RecycledViewPool,
) : BaseViewHolder<CommunitiesDetailedBinding, CommunitiesHolder>(binding) {
    private val _adapter = DetailedCommunitiesAdapter(adapterDelegates)

    init {
        with(binding.recyclerViewHorizontalItems) {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setRecycledViewPool(this@HorizontalBunchOfCommunitiesViewHolder.recycledViewPool)
        }
    }

    override fun onBind(item: CommunitiesHolder) {
        super.onBind(item)
        binding.recyclerViewHorizontalItems.onRestoreState(item.state)
        _adapter.submitList(item.communities)
    }

    override fun onViewDetached() {
        item.state =
            binding.recyclerViewHorizontalItems.layoutManager?.onSaveInstanceState() ?: return
    }
}