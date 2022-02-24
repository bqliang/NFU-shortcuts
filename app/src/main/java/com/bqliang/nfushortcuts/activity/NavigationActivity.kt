package com.bqliang.nfushortcuts.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.listener.MyDistributeListener
import com.bqliang.nfushortcuts.tools.mmkv
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute
import com.tencent.mmkv.MMKV


class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isFirstStart = mmkv.decodeBool("isFirstStart", true)
        val activityClass = if(isFirstStart) IntroActivity::class.java else MainActivity::class.java
        Intent(this, activityClass).let(::startActivity)

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