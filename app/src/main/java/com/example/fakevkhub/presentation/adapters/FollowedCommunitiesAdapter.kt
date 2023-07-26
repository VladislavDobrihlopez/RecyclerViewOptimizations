package com.example.fakevkhub.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.fakevkhub.presentation.delegates.AdapterDelegate
import com.example.fakevkhub.presentation.uimodels.Item
import com.example.fakevkhub.presentation.viewholders.BaseViewHolder

class FollowedCommunitiesAdapter(
    private val delegates: List<AdapterDelegate<*, *>>
) : RecyclerView.Adapter<BaseViewHolder<ViewBinding, Item>>() {
    var onClickEventListener: ((Item) -> Unit)? = null

    var items = mutableListOf<Item>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, Item> {
        val inflater = LayoutInflater.from(parent.context)
        return delegates.find { viewType == it.getLayoutIdAsViewType() }
            ?.getViewHolder(
                inflater = inflater,
                parent = parent
            )?.let {
                return it as BaseViewHolder<ViewBinding, Item>
            } ?: throw IllegalStateException("$viewType must be handled")
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        Log.d("FollowedCommunitiesAdapter", "$position")
        return delegates.find { it.isOfNeededType(item) }
            ?.getLayoutIdAsViewType()
            ?: throw IllegalStateException("There are no adapter delegates that are able to work with item №$position")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, Item>, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}