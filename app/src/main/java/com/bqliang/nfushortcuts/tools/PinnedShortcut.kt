package com.bqliang.nfushortcuts.tools

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.widget.Toast
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.Shortcut

fun createPinnedShortcut(labelResId:Int, iconResourceId:Int, intent: Intent, shortcutId: Shortcut){

    val shortcutManager = MyApplication.context
        .getSystemService(ShortcutManager::class.java)

    if (shortcutManager!!.isRequestPinShortcutSupported){

        val pinShortcutInfo = ShortcutInfo.Builder(MyApplication.context, "my_shortcut_" + shortcutId.toString().lowercase())
            .setIcon(Icon.createWithResource(MyApplication.context, iconResourceId))
            .setShortLabel(MyApplication.context.getString(labelResId))
            .setIntent(intent)
            .build()

        shortcutManager.requestPinShortcut(pinShortcutInfo, null)
        (MyApplication.context.resources.getString(R.string.tooltip_create_pinned_shortcut) + "-" + labelResId).showToast()
    }else{
        MyApplication.context.resources.getString(R.string.error_create_pinned_shortcut).showToast(Toast.LENGTH_LONG)
    }
}