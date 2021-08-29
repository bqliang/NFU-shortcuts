package com.bqliang.nfushortcuts.dialog

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.activity.MainActivity
import com.bqliang.nfushortcuts.tools.MyApplication
import com.bqliang.nfushortcuts.tools.SharedPreferencesUtil
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.dialog.MaterialAlertDialogBuilder

private const val KEY = "if_do_not_remind"

fun tipsForPinnedShortcutAlertDialog(activity: MainActivity){

    var ifDoNotRemind = SharedPreferencesUtil.getBoolean(KEY, false)

    if (!ifDoNotRemind){
        val view = LayoutInflater.from(activity).inflate(R.layout.do_not_remind_again_layout, null)
        view.findViewById<MaterialCheckBox>(R.id.checkbox_do_not_remind)
            .setOnCheckedChangeListener { _, isChecked ->
                ifDoNotRemind = isChecked
            }

        MaterialAlertDialogBuilder(activity)
            .setTitle(R.string.tooltip_create_pinned_shortcut)
            .setMessage(R.string.tips_of_grant_permission)
            .setView(view)
            .setPositiveButton(R.string.go_to_setting){ _, _ ->
                SharedPreferencesUtil.saveBoolean(KEY, ifDoNotRemind)
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", MyApplication.context.packageName, null)
                intent.data = uri
                activity.startActivity(intent)
            }
            .setNegativeButton(R.string.already_know){ _, _ ->
                SharedPreferencesUtil.saveBoolean(KEY, ifDoNotRemind)
            }
            .show()
    }else R.string.tooltip_create_pinned_shortcut.showToast()
}