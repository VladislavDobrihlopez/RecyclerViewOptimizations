package com.example.fakevkhub.presentation.adapters.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class InnerDetailedCommunityItemDecoration(
    private val innerDivider: Int,
    private val outerDivider: Int,
    private val excludedViewTypes: List<Int> = listOf()
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val viewHolder = parent.getChildViewHolder(view)
        if (excludedViewTypes.contains(viewHolder.itemViewType)) {
            return
        }

        val currentPosition = viewHolder.bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: viewHolder.oldPosition
        val lastPosition = parent.adapter?.itemCount?.minus(1) ?: return

        val innerHorizontalDivider = innerDivider / 2
        val outerHorizontalDivider = outerDivider / 2

        outRect.apply {
            left = innerHorizontalDivider
            right = innerHorizontalDivider
        }

        if (currentPosition == 0) {
            outRect.apply {
                left = outerHorizontalDivider
            }
        }

        if (currentPosition == lastPosition) {
            outRect.apply {
                right = outerHorizontalDivider
            }
        }
    }
}