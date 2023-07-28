package com.example.fakevkhub.presentation.adapters.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecoration(
    private val divider: Int,
    private val excludedViewTypes: List<Int> = listOf()
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (excludedViewTypes.contains(parent.getChildViewHolder(view).itemViewType)) {
            return
        }
//        if (viewType != parent.getChildViewHolder(view).itemViewType) {
//            return
//        }

        val oneSideVerticalPadding = divider / 2

        outRect.apply {
            top = oneSideVerticalPadding
            bottom = oneSideVerticalPadding
            left = oneSideVerticalPadding * 2
            right = oneSideVerticalPadding * 2
        }
    }
}