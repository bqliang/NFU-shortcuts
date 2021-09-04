package com.bqliang.nfushortcuts.activity.temp

import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.service.MyService
import com.bqliang.nfushortcuts.tools.showToast
import com.microsoft.appcenter.analytics.Analytics

class CaptivePortalLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val wifiManager = getSystemService(WIFI_SERVICE) as WifiManager
        if (wifiManager.isWifiEnabled){
            val intent = Intent(this, MyService::class.java)
            startForegroundService(intent)
            Analytics.trackEvent("Captive Portal Login")
        }else R.string.wifi_not_enable.showToast()
        finish()
    }
}