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
            intent.data = Uri.parse("taobao://huodong.m.taobao.com/act/snipcodeupdate.html?_ariver_appid=11509317&app=chrome&cpp=1&x_object_type=miniapp&query=from%3Dpinpai&suid=b76ebea2-58b3-46f1-a3d2-4986e54ba3e0&point=%7B%22from%22%3A%22h5%22%2C%22url%22%3A%22https%3A%2F%2Fg.alicdn.com%2Fminiapp-biz%2Ftemporary-huodong-huanduan%2F0.0.1%2Findex.html%22%2C%22h5_uid%22%3A%22xtJ2GVzgCSMCAQ6V0JG3lwUD%22%7D&spm=a21bpk.21128592.0.0&cv=null&sourceType=other&processByTRiver=true&bxsign=scdQhsaEFrgE0Ohl-470q_0vnHFFIPsYKjt62ovlXPvTTLA9rloD3ov8lSKERHnf3fBcpO1eC9zQkEuIHBAoomahrOG1QQbO8GKcZHFA0x-_H4&x_miniapp_id=11509317&un=0cb30115ab892e626b6c0986c1a65891&from=pinpai&short_name=h.f1gsxJq&sm=9a1cc4&page=pages%2Findex%2Findex&shareurl=true&share_crt_v=1&ut_sk=1.YFGv2Y9m%2Ff0DAKjLXArcnLu5_21646297_1630328949532.QRCodeAnti.NewMiniapp")
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