package com.bqliang.nfushortcuts.dialog

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.MyItem
import com.bqliang.nfushortcuts.R
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
                when (which) {
                    0 -> {
                        val uri = Uri.parse("https://github.com/bqliang")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        MyApplication.context.startActivity(intent)
                    }
                    1 -> openAlipay(FEEDING_DEVELOPER)
                    2 -> openAlipay(LIBRARY_CARD)
                    3 -> openAlipay(CAMPUS_BUS)
                    4 -> openAlipay(ACCESS_CODE)
                    5 -> openAlipay(QUICK_SCAN_QRCODE)
                }
                dialog.dismiss()
                activity.finish()
            }
            .setOnCancelListener {
                activity.finish()
            }
            .create()


        val view = LayoutInflater.from(activity).inflate(R.layout.footer_view_layout, null)

        mainAlertDialog.listView.addFooterView(view)

        // 列表长按事件监听
        mainAlertDialog.listView.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { _, _, position, _ ->
                when(position){
                    0 -> "(´,,•∀•,,`)".showToast(Toast.LENGTH_LONG)
                    1 -> "I am so hungry!".showToast()
                    2 -> MyApplication.context.resources.getText(R.string.electronic_library_card).toString().showToast()
                    3 -> MyApplication.context.resources.getText(R.string.campus_bus).toString().showToast()
                    4 -> MyApplication.context.resources.getText(R.string.access_code).toString().showToast()
                    5 -> MyApplication.context.resources.getText(R.string.no_scan_pass).toString().showToast()
                }
                return@OnItemLongClickListener true
            }

        view.findViewById<MaterialCardView>(R.id.card).setOnClickListener {
            activity.startForegroundService(Intent(activity, MyService::class.java))
            mainAlertDialog.dismiss()
            activity.finish()
        }

        view.findViewById<MaterialCardView>(R.id.card_of_setting).setOnClickListener{
            mainAlertDialog.hide()
            SettingAlertDialog(activity,mainAlertDialog)
        }

        mainAlertDialog.show()
    }
}