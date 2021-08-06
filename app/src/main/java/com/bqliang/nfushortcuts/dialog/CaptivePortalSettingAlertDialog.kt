package com.bqliang.nfushortcuts.dialog

import android.app.Activity
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.tools.MyApplication
import com.bqliang.nfushortcuts.tools.saveIdPassword
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CaptivePortalSettingAlertDialog(activity: Activity, mainAlertDialog: AlertDialog? = null) {

    init {
        val sp = MyApplication.context
            .getSharedPreferences("app_data", AppCompatActivity.MODE_PRIVATE)

        val views = LayoutInflater.from(activity).inflate(R.layout.id_pw_setting, null)
        val id = views.findViewById<TextView>(R.id.id_input)
        val password = views.findViewById<TextView>(R.id.pw_input)
        id.text = sp.getString("id","")
        password.text = sp.getString("password", "")

        MaterialAlertDialogBuilder(activity)
            .setTitle(MyApplication.context.resources.getText(R.string.please_enter_id_pwd))
            .setView(views)
            .setPositiveButton(MyApplication.context.resources.getText(R.string.save)) { dialog, _ ->
                saveIdPassword(id.text.toString(), password.text.toString())
                MyApplication.context.resources.getText(R.string.save_successfully).toString().showToast()
                mainAlertDialog?.show()
                dialog.dismiss()
            }
            .setNegativeButton(MyApplication.context.resources.getText(R.string.cancel)){ dialog, _ ->
                mainAlertDialog?.show()
                dialog.dismiss()
            }
            .setOnCancelListener { dialog ->
                mainAlertDialog?.show()
                dialog.dismiss()
            }
            .create()
            .show()
    }
}