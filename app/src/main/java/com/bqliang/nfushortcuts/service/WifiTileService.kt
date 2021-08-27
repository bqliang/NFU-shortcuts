package com.bqliang.nfushortcuts.service

import android.content.Intent
import android.service.quicksettings.TileService
import com.bqliang.nfushortcuts.activity.TempCaptivePortalLoginActivity

class WifiTileService: TileService() {

    override fun onClick() {
        super.onClick()
        val intent = Intent(this, TempCaptivePortalLoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivityAndCollapse(intent)
    }
}