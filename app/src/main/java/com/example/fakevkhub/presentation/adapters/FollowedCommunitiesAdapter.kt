package com.example.fakevkhub.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.fakevkhub.presentation.adapters.delegates.AdapterDelegate
import com.example.fakevkhub.presentation.adapters.viewholders.BaseViewHolder
import com.example.fakevkhub.presentation.uimodels.Item

class FollowedCommunitiesAdapter(
    private val adapterDelegates: List<AdapterDelegate<*, *>>
) : ListAdapter<Item, BaseViewHolder<ViewBinding, Item>>(
    CommunitiesDiffUtils(
        adapterDelegates
    )
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, Item> {
        val inflater = LayoutInflater.from(parent.context)
        adapterDelegates.find { viewType == it.getLayoutIdAsViewType() }
            ?.getViewHolder(
                inflater = inflater,
                parent = parent
            )?.let {
                return it as BaseViewHolder<ViewBinding, Item>
            } ?: throw IllegalStateException("$viewType must be handled")
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        Log.d("FollowedCommunitiesAdapter", "$position")
        adapterDelegates.find { it.isOfNeededType(item) }
            ?.getLayoutIdAsViewType()
            ?.let { layoutId ->
                return layoutId
            }
            ?: throw IllegalStateException("There are no adapter delegates that are able to work with item №$position")
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewBinding, Item>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.onBind(getItem(position), payloads)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, Item>, position: Int) {
        holder.onBind(getItem(position))
    }
}