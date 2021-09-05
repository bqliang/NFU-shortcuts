package com.bqliang.nfushortcuts.activity.temp

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.model.DependentApp
import com.bqliang.nfushortcuts.tools.AppUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CaiNiaoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK


        if (AppUtil.isAppInstalled(DependentApp.CAINIAO)){
            intent.component = ComponentName("com.cainiao.wireless", "com.cainiao.wireless.homepage.view.activity.HomePageActivity")
            startActivity(intent)
            finish()
        }else if (AppUtil.isAppInstalled(DependentApp.TAOBAO)){
            intent.data = Uri.parse("taobao://g.alicdn.com/miniapp-biz/temporary-huodong-huanduan/0.0.1/index.html?_ariver_appid=11509317")
            startActivity(intent)
            finish()
        }else{
            MaterialAlertDialogBuilder(this).apply {
                setMessage(R.string.install_taobao_or_cainiao_first)
                setPositiveButton(R.string.download_taobao) { dialog, _ ->
                    intent.data = Uri.parse("market://details?id=${DependentApp.TAOBAO}")
                    dialog.dismiss()
                    startActivity(intent)
                    finish()
                }
                setNegativeButton(R.string.download_cainiao) { dialog, _ ->
                    intent.data = Uri.parse("market://details?id=${DependentApp.CAINIAO}")
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