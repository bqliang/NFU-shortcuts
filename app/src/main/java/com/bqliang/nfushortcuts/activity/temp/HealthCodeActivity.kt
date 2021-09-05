package com.bqliang.nfushortcuts.activity.temp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.model.DependentApp
import com.bqliang.nfushortcuts.tools.AppUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HealthCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        if (AppUtil.isAppInstalled(DependentApp.UNIONPAY)){
            intent.data = Uri.parse("upwallet://applet?toLink=https://ywtb.fpsd.unionpay.com/pub/jmas/jmasbucket/jmopen_files/unzip/4c94e5e6d269477983c5992bfd97d95e/gdykm/index.html#&encryptAppId=bb2b4babcbfd06bc")
            startActivity(intent)
            finish()
        }else{
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.not_yet_install_unionpay)
                .setMessage(R.string.download_unionpay_dialog_message)
                .setPositiveButton(R.string.ok){ dialog, _ ->
                    intent.data = Uri.parse("market://details?id=${DependentApp.UNIONPAY}")
                    startActivity(intent)
                    dialog.dismiss()
                    finish()
                }
                .setNegativeButton(R.string.do_not_need){ dialog, _ ->
                    dialog.dismiss()
                    finish()
                }
                .setOnCancelListener { dialog ->
                    dialog.dismiss()
                    finish()
                }
                .show()
        }
    }
}