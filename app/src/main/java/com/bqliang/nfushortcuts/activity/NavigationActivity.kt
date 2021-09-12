package com.bqliang.nfushortcuts.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.listener.MyDistributeListener
import com.bqliang.nfushortcuts.tools.SharedPreferencesUtil
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute


class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent()
        val isFirstStart = SharedPreferencesUtil.getBoolean("isFirstStart", true)

        if (isFirstStart) intent.setClass(this, IntroActivity::class.java)
        else intent.setClass(this, MainActivity::class.java)

        startActivity(intent)

        AppCenter.start(
            application, "f3e1fad6-dc60-4b23-8101-b7c98999bfdb",
            Analytics::class.java, Crashes::class.java, Distribute::class.java
        )
        Distribute.disableAutomaticCheckForUpdate()
        Distribute.setEnabledForDebuggableBuild(true)
        Distribute.setListener(MyDistributeListener())

        finish()
    }
}