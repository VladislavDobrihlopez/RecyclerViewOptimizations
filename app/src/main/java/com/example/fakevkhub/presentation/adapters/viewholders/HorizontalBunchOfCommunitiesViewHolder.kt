package com.example.fakevkhub.presentation.adapters.viewholders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakevkhub.databinding.CommunitiesDetailedBinding
import com.example.fakevkhub.presentation.adapters.DetailedCommunitiesAdapter
import com.example.fakevkhub.presentation.adapters.delegates.AdapterDelegate
import com.example.fakevkhub.presentation.uimodels.CommunitiesHolder

class HorizontalBunchOfCommunitiesViewHolder(
    binding: CommunitiesDetailedBinding,
    adapterDelegates: List<AdapterDelegate<*, *>>
) : BaseViewHolder<CommunitiesDetailedBinding, CommunitiesHolder>(binding) {
    private val _adapter = DetailedCommunitiesAdapter(adapterDelegates)

    init {
        with(binding.recyclerViewHorizontalItems) {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    override fun onBind(item: CommunitiesHolder) {
        _adapter.submitList(item.communities)
    }

    override fun onBind(item: CommunitiesHolder, payloads: List<Any>) {
        onBind(item)
    }
}