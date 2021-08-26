package com.bqliang.nfushortcuts.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.model.DependentApp
import com.bqliang.nfushortcuts.tools.AppUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TempHealthCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        if (AppUtil.isAppInstalled(DependentApp.UNIONPAY)){
            intent.data = Uri.parse("upwallet://applet?encryptAppId=bb2b4babcbfd06bc&toLink=https%3A%2F%2Fywtb.fpsd.unionpay.com%2Fpub%2Fjmas%2Fjmasbucket%2Fjmopen_files%2Funzip%2F4c94e5e6d269477983c5992bfd97d95e%2Fgdykm%2Findex.html%23%2F%3Fapplicationid%3D9fc01b17589946619b461489a0f35c5c%26channelid%3D0108%26state%3Dsuccess%26openid%3DiVn790TUJoNn2WcCoyyGSvpcbJc1kFC490yEZcz%252BHicHKko7mk9ut%252BbXC447RjDW&scenarioId=1006")
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
                .setNegativeButton(R.string.no){ dialog, _ ->
                    dialog.dismiss()
                    finish()
                }
                .show()
        }
    }
}