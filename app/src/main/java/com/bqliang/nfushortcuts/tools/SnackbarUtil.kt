package com.bqliang.nfushortcuts.tools

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun String.showSnackBar(view: View, duration: Int = Snackbar.LENGTH_SHORT){
    Snackbar.make(view, this, duration).show()
}

fun Int.showSnackBar(view: View, duration: Int = Snackbar.LENGTH_SHORT){
    Snackbar.make(view, this, duration).show()
}