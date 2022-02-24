package com.bqliang.nfushortcuts.dialog

import android.app.Activity
import android.view.LayoutInflater
import android.widget.TextView
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.tools.mmkv
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.microsoft.appcenter.analytics.Analytics

class CaptivePortalSettingAlertDialog(activity: Activity) {

    init {
        val views = LayoutInflater.from(activity).inflate(R.layout.id_pw_setting, null)
        val id = views.findViewById<TextView>(R.id.id_input)
        val password = views.findViewById<TextView>(R.id.pw_input)
        id.text = mmkv.decodeString("id","")
        password.text = mmkv.decodeString("password", "")

        MaterialAlertDialogBuilder(activity).apply {
            setTitle(R.string.please_enter_id_pwd)
            setView(views)
            setPositiveButton(R.string.save) { _, _ ->
                mmkv.encode("id", id.text.toString())
                mmkv.encode("password", password.text.toString())
                R.string.save_successfully.showToast()
                Analytics.trackEvent("Save Account Info")
            }
            setNegativeButton(R.string.cancel){ _, _ -> }
            create()
            show()
        }
    }
}