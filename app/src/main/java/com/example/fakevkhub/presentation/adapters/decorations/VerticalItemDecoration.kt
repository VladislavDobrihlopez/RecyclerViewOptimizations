package com.example.fakevkhub.presentation.adapters.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fakevkhub.R
import com.example.fakevkhub.presentation.isNextViewOfTheSameType
import com.example.fakevkhub.presentation.isPreviousViewOfTheSameType

class VerticalItemDecoration(
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

        val innerVerticalDivider = innerDivider / 2
        val currentPosition =
            viewHolder.bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: viewHolder.oldPosition
        val viewType = viewHolder.itemViewType

        parent.adapter?.let { adapter ->
            outRect.apply {
                bottom = //if (adapter.isNextViewOfTheSameType(currentPosition, viewType)|| adapter.isNextViewOfTheSameType(currentPosition, R.layout.section)) {
                    innerVerticalDivider
                //} else {
                    //outerDivider
               // }
                top = //if (adapter.isPreviousViewOfTheSameType(currentPosition, viewType)|| adapter.isPreviousViewOfTheSameType(currentPosition, R.layout.section)) {
                    innerVerticalDivider
                //} else {
                //    outerDivider
                //}
            }
        }
    }
}