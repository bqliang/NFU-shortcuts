package com.bqliang.nfushortcuts.tools

import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.widget.Toast
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.activity.MainActivity
import com.bqliang.nfushortcuts.dialog.tipsForPinnedShortcutAlertDialog
import com.bqliang.nfushortcuts.model.Shortcut

fun createPinnedShortcut(shortcut:Shortcut, activity: MainActivity){

    var labelResId = -1
    var iconResourceId = -1

    when(shortcut){
        Shortcut.LIBRARY_CARD -> {
            labelResId = R.string.library_card
            iconResourceId = R.mipmap.item_library_card
        }
        Shortcut.CAMPUS_BUS -> {
            labelResId = R.string.campus_bus
            iconResourceId = R.mipmap.item_campus_bus
        }
        Shortcut.ACCESS_CODE -> {
            labelResId = R.string.access_code
            iconResourceId = R.mipmap.item_access_code
        }
        Shortcut.QUICK_SCAN_QRCODE -> {
            labelResId = R.string.no_scan_pass
            iconResourceId = R.mipmap.item_no_scan_pass
        }
        Shortcut.CAPTIVE_PORTAL_LOGIN -> {
            labelResId = R.string.captive_portal_login
            iconResourceId = R.mipmap.item_captive_portal_login
        }
        Shortcut.FEED_DEVELOPER -> { }
        Shortcut.KFC -> {
            labelResId = R.string.kfc
            iconResourceId = R.mipmap.item_kfc
        }
        Shortcut.ALIPAY_CODE -> {
            labelResId = R.string.alipay_code
            iconResourceId = R.mipmap.item_alipay_code
        }
        Shortcut.HEALTH_CODE -> {
            labelResId = R.string.health_code
            iconResourceId = R.mipmap.item_health_code
        }
    }


    val shortcutManager = MyApplication.context
        .getSystemService(ShortcutManager::class.java)
    val label = MyApplication.context.getString(labelResId)

    if (shortcutManager!!.isRequestPinShortcutSupported){

        val pinShortcutInfo = ShortcutInfo.Builder(MyApplication.context, "my_shortcut_" + shortcut.toString().lowercase())
            .setIcon(Icon.createWithResource(MyApplication.context, iconResourceId))
            .setShortLabel(label)
            .setIntent(getIntent(shortcut))
            .build()

        shortcutManager.requestPinShortcut(pinShortcutInfo, null)
        tipsForPinnedShortcutAlertDialog(activity)
    }else{
        MyApplication.context.resources.getString(R.string.error_create_pinned_shortcut).showToast(Toast.LENGTH_LONG)
    }
}