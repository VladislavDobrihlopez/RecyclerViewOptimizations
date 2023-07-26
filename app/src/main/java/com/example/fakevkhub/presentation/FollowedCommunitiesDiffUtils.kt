package com.example.fakevkhub.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.fakevkhub.presentation.delegates.AdapterDelegate
import com.example.fakevkhub.presentation.uimodels.Item

class FollowedCommunitiesDiffUtils(
    private val delegateAdapters: List<AdapterDelegate<*, *>>
) : DiffUtil.ItemCallback<Item>() {
    private fun getDiffItemCallback(item: Item): DiffUtil.ItemCallback<Item> {
        return delegateAdapters.find { it.isOfNeededType(item) }
            ?.getDiffItemCallback()
            ?.let { it as DiffUtil.ItemCallback<Item> }
            ?: throw IllegalStateException("There is no AdapterDelegate that can handle areItemsTheSame of type: ${item::class}")
    }

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        if (oldItem::class != newItem::class) {
            return false;
        }
        return getDiffItemCallback(oldItem).areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        if (oldItem::class != newItem::class) {
            return false;
        }
        return getDiffItemCallback(newItem).areContentsTheSame(oldItem, newItem)
    }

    override fun getChangePayload(oldItem: Item, newItem: Item): Any? {
        if (oldItem::class != newItem::class) {
            return false
        }
        return getDiffItemCallback(oldItem).getChangePayload(oldItem, newItem)
    }
}