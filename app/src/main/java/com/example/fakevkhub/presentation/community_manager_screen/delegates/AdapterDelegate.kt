package com.example.fakevkhub.presentation.community_manager_screen.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.example.fakevkhub.presentation.community_manager_screen.BaseViewHolder
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.ListedItem

interface AdapterDelegate<V: ViewBinding, I: ListedItem> {
    fun isOfNeededType(item: ListedItem): Boolean
    @LayoutRes
    fun getViewType(): Int
    fun createViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): BaseViewHolder<V, I>
    fun getDiffItemCallback(): DiffUtil.ItemCallback<I>
}