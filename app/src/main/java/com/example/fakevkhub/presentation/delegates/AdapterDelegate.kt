package com.example.fakevkhub.presentation.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.fakevkhub.presentation.uimodels.Item
import com.example.fakevkhub.presentation.viewholders.BaseViewHolder

/*
Обязанность - инкапсулятор вьюхолдера и вьютайпа, предоставляет их наружу
 */
abstract class AdapterDelegate<V : ViewBinding, I : Item> {
    abstract fun isOfNeededType(itemForChecking: Item): Boolean
    abstract fun getViewHolder(inflater: LayoutInflater, parent: ViewGroup): BaseViewHolder<V, I>
    abstract fun getLayoutIdAsViewType(): Int
    abstract fun getDiffItemCallback(): DiffUtil.ItemCallback<I>
    open fun setupViewPoolSize(recyclerView: RecyclerView, maxPoolSize: Int) {
        recyclerView.recycledViewPool.setMaxRecycledViews(getLayoutIdAsViewType(), maxPoolSize)
    }
}