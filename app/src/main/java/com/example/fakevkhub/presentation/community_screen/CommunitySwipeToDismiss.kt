package com.example.fakevkhub.presentation.community_screen

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.fakevkhub.R
import com.example.fakevkhub.presentation.community_manager_screen.BaseViewHolder
import com.example.fakevkhub.presentation.community_manager_screen.uimodels.ListedItem

class CommunitySwipeToDismiss(
    private val onItemRemoved: (Int) -> Unit
) : ItemTouchHelper.SimpleCallback(ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val adapter = recyclerView.adapter as ListAdapter<ListedItem, BaseViewHolder<ViewBinding, ListedItem>>

        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onItemRemoved(viewHolder.bindingAdapterPosition)
    }

    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
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
        if (actionState != ItemTouchHelper.ACTION_STATE_SWIPE || viewHolder.itemViewType != R.layout.community_item) return

        val itemView: View = viewHolder.itemView

        val alpha = 0.5f.coerceAtMost(Math.abs(dX) / viewHolder.itemView.width)
        val p = Paint().also { it.color = Color.argb((alpha * 255).toInt(), 255, 0, 0) }

        c.drawRect(
            itemView.left.toFloat(),
            itemView.top.toFloat(),
            itemView.right.toFloat() + Math.abs(dX) / 2,
            itemView.bottom.toFloat(),
            p
        )

        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}