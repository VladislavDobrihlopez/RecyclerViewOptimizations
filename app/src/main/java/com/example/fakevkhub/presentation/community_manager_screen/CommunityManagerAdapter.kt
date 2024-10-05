package com.example.fakevkhub.presentation.community_manager_screen

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.fakevkhub.presentation.community_manager_screen.delegates.AdapterDelegate
import com.example.fakevkhub.presentation.community_manager_screen.delegates.AdviceOptionItemAdapterDelegate
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.ListedItem

class CommunityManagerAdapter(
    private val adapterDelegates: List<AdapterDelegate<ViewBinding, ListedItem>>
) : ListAdapter<ListedItem, BaseViewHolder<ViewBinding, ListedItem>>(CustomDiffUtil(adapterDelegates)) {
    override fun getItemViewType(position: Int): Int {
        return adapterDelegates.find { it.isOfNeededType(getItem(position)) }?.getViewType()
            ?: throw IllegalStateException("$position is unhandled in getItemViewType")
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, ListedItem> {
        Log.d(TAG, "onCreateViewHolder: viewType = $viewType")
        val inflater = LayoutInflater.from(parent.context)
        return (adapterDelegates.find { it.getViewType() == viewType }
            ?.createViewHolder(inflater, parent)
            ?: throw IllegalStateException("$viewType is unhandled in onCreateViewHolder")) as BaseViewHolder<ViewBinding, ListedItem>
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, ListedItem>, position: Int) {
        Log.d(TAG, "onBindViewHolder: $position")
        holder.onBind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewBinding, ListedItem>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        Log.d("TEST_PAYLOAD", "onBindViewHolder $payloads")
        if (payloads.isNullOrEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.onBind(getItem(position), payloads)
        }
    }

    companion object {
        const val TAG = "CommunityManagerAdapter"
    }
}