package com.bqliang.nfushortcuts.view

import androidx.recyclerview.widget.GridLayoutManager

class MySpanSizeLookup:GridLayoutManager.SpanSizeLookup() {
    // 双列显示
    override fun getSpanSize(position: Int) = 1
}