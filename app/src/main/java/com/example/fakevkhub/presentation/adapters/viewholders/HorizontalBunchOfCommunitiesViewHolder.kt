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
) : BaseViewHolder<CommunitiesDetailedBinding, CommunitiesHolder>(binding) {
    private val _adapter = DetailedCommunitiesAdapter(adapterDelegates)
    //private var xScrollState: Int = 0

    init {
        with(binding.recyclerViewHorizontalItems) {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    override fun onBind(item: CommunitiesHolder) {
        super.onBind(item)
        //xScrollState = binding.recyclerViewHorizontalItems.scroll
        binding.recyclerViewHorizontalItems.onRestoreState(item.state)
        _adapter.submitList(item.communities)
        //binding.recyclerViewHorizontalItems.scrollX = xScrollState
    }

    override fun onBind(item: CommunitiesHolder, payloads: List<Any>) {
        super.onBind(item, payloads)

        if (payloads.last() == true)
            _adapter.submitList(item.communities)
    }

    override fun onViewDetached() {
        item.state =
            binding.recyclerViewHorizontalItems.layoutManager?.onSaveInstanceState() ?: return
    }
}