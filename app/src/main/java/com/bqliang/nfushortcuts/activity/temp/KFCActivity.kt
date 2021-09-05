package com.bqliang.nfushortcuts.activity.temp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.model.DependentApp
import com.bqliang.nfushortcuts.tools.AppUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class KFCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(AppUtil.isAppInstalled(DependentApp.KFC)){
            val intent = packageManager.getLaunchIntentForPackage(DependentApp.KFC)
            startActivity(intent)
            finish()
        }else if (AppUtil.isAppInstalled(DependentApp.ALIPAY)) {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                data = Uri.parse("alipays://platformapi/startapp?appId=2018090361289233&page=preorderHome%2Fpages%2Fenter%2Fhome%2Findex")
            }
            startActivity(intent)
            finish()
        } else {
            MaterialAlertDialogBuilder(this).apply {
                setMessage(R.string.temp_kfc_dialog_msg)
                setPositiveButton(R.string.download_kfc) { dialog, _ ->
                    intent.data = Uri.parse("market://details?id=${DependentApp.KFC}")
                    dialog.dismiss()
                    startActivity(intent)
                    finish()
                }
                setNegativeButton(R.string.download_alipay) { dialog, _ ->
                    intent.data = Uri.parse("market://details?id=${DependentApp.ALIPAY}")
                    startActivity(intent)
                    dialog.dismiss()
                    finish()
                }
                setNeutralButton(R.string.cancel) { _, _ -> finish() }
                setOnCancelListener { finish() }
                create()
                show()
            }
        }
    }
}