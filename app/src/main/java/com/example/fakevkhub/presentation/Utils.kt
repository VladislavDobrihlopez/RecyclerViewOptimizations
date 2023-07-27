package com.example.fakevkhub.presentation

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.Adapter<*>.isPreviousViewOfTheSameType(currentAdapterPosition: Int, viewType: Int): Boolean {
    val prevPosition = currentAdapterPosition - 1
    if (prevPosition <= 0) {
        return false
    }
    return getItemViewType(prevPosition) == viewType
}

fun RecyclerView.Adapter<*>.isNextViewOfTheSameType(currentAdapterPosition: Int, viewType: Int): Boolean {
    val nextPosition = currentAdapterPosition + 1
    if (nextPosition >= itemCount) {
        return false
    }
    return getItemViewType(nextPosition) == viewType
}