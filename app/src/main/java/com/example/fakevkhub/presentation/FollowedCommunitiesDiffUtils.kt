package com.example.fakevkhub.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.fakevkhub.presentation.delegates.AdapterDelegate
import com.example.fakevkhub.presentation.uimodels.Item

class FollowedCommunitiesDiffUtils(
    private val olds: List<Item>,
    private val newbies: List<Item>,
    private val delegateAdapters: List<AdapterDelegate<*, *>>
) : DiffUtil.Callback() {
    override fun getOldListSize() = olds.size

    override fun getNewListSize() = newbies.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = olds[oldItemPosition]
        val newItem = newbies[newItemPosition]
        if (oldItem::class != newItem::class) {
            return false;
        }
        return getDiffItemCallback(oldItem).areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = olds[oldItemPosition]
        val newItem = newbies[newItemPosition]
        if (oldItem::class != newItem::class) {
            return false;
        }
        return getDiffItemCallback(newItem).areContentsTheSame(oldItem, newItem)
    }

    private fun getDiffItemCallback(item: Item): DiffUtil.ItemCallback<Item> {
        return delegateAdapters.find { it.isOfNeededType(item) }
            ?.getDiffItemCallback()
            ?.let { it as DiffUtil.ItemCallback<Item> }
            ?: throw IllegalStateException("There is no AdapterDelegate that can handle areItemsTheSame of type: ${item::class}")
    }
}