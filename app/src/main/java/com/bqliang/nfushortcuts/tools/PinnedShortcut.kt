package com.bqliang.nfushortcuts.tools

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.widget.Toast
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.Shortcut

fun createPinnedShortcut(label:String, iconResourceId:Int, intent: Intent, shortcutId: Shortcut){

    val shortcutManager = MyApplication.context
        .getSystemService(ShortcutManager::class.java)

    if (shortcutManager!!.isRequestPinShortcutSupported){

        val pinShortcutInfo = ShortcutInfo.Builder(MyApplication.context, "my_shortcut_" + shortcutId.toString().lowercase())
            .setIcon(Icon.createWithResource(MyApplication.context, iconResourceId))
            .setShortLabel(label)
            .setIntent(intent)
            .build()

        shortcutManager.requestPinShortcut(pinShortcutInfo, null)
        (MyApplication.context.resources.getText(R.string.tooltip_create_pinned_shortcut).toString() + "-" + label).showToast()
    }else{
        MyApplication.context.resources.getText(R.string.error_create_pinned_shortcut).toString().showToast(Toast.LENGTH_LONG)
    }
}