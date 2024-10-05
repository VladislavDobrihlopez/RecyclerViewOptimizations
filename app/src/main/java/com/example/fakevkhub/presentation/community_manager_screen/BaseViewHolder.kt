package com.example.fakevkhub.presentation.community_manager_screen

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.ListedItem

abstract class BaseViewHolder<V : ViewBinding, I : ListedItem>(val binding: V): ViewHolder(binding.root) {
    public abstract fun onBind(item: I)
    public open fun onBind(item: I, payload: List<Any>) = Unit
}