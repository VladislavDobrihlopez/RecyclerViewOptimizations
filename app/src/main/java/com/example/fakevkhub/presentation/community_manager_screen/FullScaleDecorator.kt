package com.example.fakevkhub.presentation.community_manager_screen

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.fakevkhub.R

class FullScaleDecorator: ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildViewHolder(view).itemViewType == R.layout.community_option_advice_item) {
            val v = parent.getChildViewHolder(view)
            v.itemView.layoutParams.apply {
                width = 1000
                height = 1000
            }
        }
    }
}