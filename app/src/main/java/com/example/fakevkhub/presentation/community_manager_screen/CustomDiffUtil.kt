package com.example.fakevkhub.presentation.community_manager_screen

import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.fakevkhub.presentation.community_manager_screen.delegates.AdapterDelegate
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.ListedItem

class CustomDiffUtil(
    private val delegates: List<AdapterDelegate<ViewBinding, ListedItem>>
) : DiffUtil.ItemCallback<ListedItem>() {
    private fun checkEquality(item1: ListedItem, item2: ListedItem): Boolean {
        return item1::class.java == item2::class.java
    }

    override fun areItemsTheSame(oldItem: ListedItem, newItem: ListedItem): Boolean {
        if (!checkEquality(oldItem, newItem)) return false
        return delegates
            .find { it.isOfNeededType(oldItem) }
            ?.getDiffItemCallback()
            ?.areItemsTheSame(oldItem, newItem)
            ?: throw IllegalStateException()
    }

    override fun areContentsTheSame(oldItem: ListedItem, newItem: ListedItem): Boolean {
        if (!checkEquality(oldItem, newItem)) return false
        return delegates
            .find { it.isOfNeededType(oldItem) }
            ?.getDiffItemCallback()
            ?.areContentsTheSame(oldItem, newItem)
            ?: throw IllegalStateException()
    }

    override fun getChangePayload(oldItem: ListedItem, newItem: ListedItem): Any? {
        if (!checkEquality(oldItem, newItem)) return false
        return delegates
            .find { it.isOfNeededType(oldItem) }
            ?.getDiffItemCallback()
            ?.getChangePayload(oldItem, newItem)
    }
}