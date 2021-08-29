package com.bqliang.nfushortcuts.activity

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.adapter.MyListAdapter
import com.bqliang.nfushortcuts.model.Driver
import com.bqliang.nfushortcuts.model.ShortcutItem
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.permissionx.guolindev.PermissionX

class CallXiaobaiActivity : AppCompatActivity() {

    private val drivers : List<Driver> by lazy {
        ArrayList<Driver>().apply {
            add(Driver(R.string.uncle, "13527758146", R.mipmap.item_call_xiaobai))
            add(Driver(R.string.aunt, "13539842533", R.mipmap.item_call_xiaobai))
            add(Driver(R.string.brother, "10086", R.mipmap.item_call_xiaobai))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PermissionX.init(this)
            .permissions(Manifest.permission.CALL_PHONE)
            .onExplainRequestReason{ scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, getString(R.string.explain_why_phone_call_permission),
                    getString(R.string.ok), getString(R.string.cancel))
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(deniedList, getString(R.string.allow_call_phone_in_setting),
                    getString(R.string.ok), getString(R.string.cancel))
            }
            .request{ allGranted, _, _ ->
                if (allGranted) showDialog()
            }
    }

    private fun showDialog(){
        val adapter = MyListAdapter(this, R.layout.driver_item_layout, drivers)
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.please_choose_driver)
            .setAdapter(adapter){ _, _ -> }
            .setNeutralButton(R.string.cancel){ _, _ -> finish() }
            .setOnCancelListener { finish() }
            .show()
    }

    fun call(phoneNum: String){
        val uri = Uri.parse("tel:$phoneNum")
        val intent = Intent(Intent.ACTION_CALL, uri)
        startActivity(intent)
        finish()
    }
}