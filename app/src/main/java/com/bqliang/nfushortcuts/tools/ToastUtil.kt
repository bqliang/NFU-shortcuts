package com.bqliang.nfushortcuts.tools

import android.widget.Toast

fun String.showToast(duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(MyApplication.context, this, duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(
        MyApplication.context,
        MyApplication.context.getString(this),
        duration)
        .show()
}