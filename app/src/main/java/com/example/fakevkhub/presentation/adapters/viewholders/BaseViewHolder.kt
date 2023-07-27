package com.example.fakevkhub.presentation.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.fakevkhub.presentation.uimodels.Item

abstract class BaseViewHolder<V : ViewBinding, I : Item>(private val binding: V) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(item: I)
    abstract fun onBind(item: I, payloads: List<Any>)
}