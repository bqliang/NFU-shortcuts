package com.bqliang.nfushortcuts.tools

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE

object ClipboardUtil {

    private val cm : ClipboardManager by lazy {
        MyApplication.context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    }

    fun copyText(text : String){
        cm.setPrimaryClip(ClipData.newPlainText(MyApplication.context.packageName, text))
    }
}