package com.bqliang.nfushortcuts.dialog

import android.content.Intent
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.Shortcut
import com.bqliang.nfushortcuts.adapter.AlertDialogAdapter
import com.bqliang.nfushortcuts.service.MyService
import com.bqliang.nfushortcuts.tools.*
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PrimaryAlertDialog(private val activity: AppCompatActivity) {

    lateinit var mainAlertDialog: AlertDialog

    init {
        setAlertDialog()
    }

    private fun setAlertDialog(){
        val adapter = AlertDialogAdapter(activity, R.layout.alertdialog_item, getData())

        mainAlertDialog = MaterialAlertDialogBuilder(activity)
            .setAdapter(adapter) { dialog, which ->
                MyApplication.context.startActivity(getMyIntent(which))
                dialog.dismiss()
                activity.finish()
            }
            .setOnCancelListener {
                activity.finish()
            }
            .create()


        val footerView = LayoutInflater.from(activity).inflate(R.layout.footer_view_layout, null)

        mainAlertDialog.listView.addFooterView(footerView)

        // 列表长按事件监听
        mainAlertDialog.listView.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { _, _, position, _ ->
                when(position){
                    0 -> "(´,,•∀•,,`)".showToast(Toast.LENGTH_LONG)
                    1 -> "I am so hungry!".showToast()
                    2 -> createPinnedShortcut(MyApplication.context.resources.getText(R.string.library_card).toString(), R.mipmap.library_card_circle, getMyIntent(position), Shortcut.LIBRARY_CARD)
                    3 -> createPinnedShortcut(MyApplication.context.resources.getText(R.string.campus_bus).toString(), R.mipmap.campus_bus_circle, getMyIntent(position), Shortcut.CAMPUS_BUS)
                    4 -> createPinnedShortcut(MyApplication.context.resources.getText(R.string.access_code).toString(),R.mipmap.access_code_circle, getMyIntent(position), Shortcut.ACCESS_CODE)
                    5 -> createPinnedShortcut(MyApplication.context.resources.getText(R.string.no_scan_pass).toString(), R.mipmap.no_scan_pass_circle, getMyIntent(position), Shortcut.QUICK_SCAN_QRCODE)
                }
                return@OnItemLongClickListener true
            }

        val captivePortalItem = footerView.findViewById<MaterialCardView>(R.id.card)

        captivePortalItem.setOnClickListener {
            activity.startForegroundService(Intent(activity, MyService::class.java))
            mainAlertDialog.dismiss()
            activity.finish()
        }

        captivePortalItem.setOnLongClickListener {
            createPinnedShortcut(MyApplication.context.resources.getString(R.string.captive_portal_login), R.mipmap.login_circle, getMyIntent(6), Shortcut.CAPTIVE_PORTAL_LOGIN)
            return@setOnLongClickListener true
        }

        footerView.findViewById<MaterialCardView>(R.id.card_of_setting).setOnClickListener{
            mainAlertDialog.hide()
            SettingAlertDialog(activity,mainAlertDialog)
        }

        mainAlertDialog.show()
    }
}