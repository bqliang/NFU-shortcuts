package com.bqliang.nfushortcuts.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.google.android.material.textview.MaterialTextView

class MyMarqueeTextView : MaterialTextView {

    constructor(@NonNull context: Context) : super(context)

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes)

    override fun isFocused() = true
}