package com.example.fakevkhub.presentation

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.fakevkhub.R

class CommunitySwipeToDismiss(
    private val onItemRemoved: (Int) -> Unit
) :
    ItemTouchHelper.SimpleCallback(ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.DOWN) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onItemRemoved(viewHolder.adapterPosition)
    }

    override fun getSwipeDirs(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return if (viewHolder.itemViewType != R.layout.community_item) {
            ItemTouchHelper.ACTION_STATE_IDLE
        } else {
            super.getSwipeDirs(recyclerView, viewHolder)
        }
    }

    override fun onChildDrawOver(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState != ItemTouchHelper.ACTION_STATE_SWIPE) return

        val itemView: View = viewHolder.itemView

        val alpha = 0.7f.coerceAtMost(dY / viewHolder.itemView.width)
        val p = Paint().also { it.color = Color.argb((alpha * 255).toInt(), 255, 0, 0) }

        c.drawRect(
            itemView.left.toFloat(),
            itemView.top.toFloat() - dY,
            itemView.right.toFloat(),
            itemView.bottom.toFloat(),
            p
        )

        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}