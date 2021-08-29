package com.bqliang.nfushortcuts.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MyItemDecoration(private val spanCount: Int) : RecyclerView.ItemDecoration() {

    private val rowSpacing = 25
    private val columnSpacing = 40

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)
        val columnOfPosition: Int = position % spanCount // view 所在的列（从0开始计数）

        outRect.left = if (columnOfPosition == 0) columnSpacing else  columnSpacing / 2

        outRect.right = if (columnOfPosition == spanCount - 1) columnSpacing else columnSpacing / 2

        if(position < spanCount) outRect.top = rowSpacing
        outRect.bottom = rowSpacing
    }
}