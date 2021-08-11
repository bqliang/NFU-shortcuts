package com.bqliang.nfushortcuts.view

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class MyItemDecoration(
    @NonNull private val rowSpacing: Int,
    @NonNull private val columnSpacing: Int
    )
    : RecyclerView.ItemDecoration() {

    private val spanCount = 2

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

        if(position < spanCount) outRect.top = rowSpacing / 2
        outRect.bottom = rowSpacing
    }
}