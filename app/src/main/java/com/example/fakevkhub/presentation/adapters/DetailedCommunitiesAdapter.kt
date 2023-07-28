package com.example.fakevkhub.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.fakevkhub.presentation.adapters.delegates.AdapterDelegate
import com.example.fakevkhub.presentation.adapters.delegates.FollowedCommunitiesDelegateAdapter
import com.example.fakevkhub.presentation.adapters.viewholders.BaseViewHolder
import com.example.fakevkhub.presentation.uimodels.Item

class DetailedCommunitiesAdapter(
    private val delegateAdapters: List<AdapterDelegate<*, *>>
) : ListAdapter<Item, BaseViewHolder<ViewBinding, Item>>(CommunitiesDiffUtils(delegateAdapters)) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, Item> {
        val inflater = LayoutInflater.from(parent.context)
        delegateAdapters.find { it.getLayoutIdAsViewType() == viewType }
            ?.getViewHolder(inflater, parent)
            ?.let {
                return it as BaseViewHolder<ViewBinding, Item>
            }
            ?: throw IllegalStateException("$viewType must be handled")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, Item>, position: Int) {
        holder.onBind(currentList[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewBinding, Item>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.onBind(currentList[position], payloads)
        }
    }

    override fun getItemViewType(position: Int): Int {
        delegateAdapters.find { it.isOfNeededType(currentList[position]) }
            ?.getLayoutIdAsViewType()
            ?.let { layoutId ->
                return layoutId
            }
            ?: throw IllegalStateException("There are no adapter delegates that are able to work with item №$position")
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<ViewBinding, Item>) {
        holder.onViewDetached()
    }


}