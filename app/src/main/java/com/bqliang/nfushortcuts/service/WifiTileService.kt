package com.bqliang.nfushortcuts.service

import android.content.Intent
import android.service.quicksettings.TileService
import com.bqliang.nfushortcuts.activity.temp.CaptivePortalLoginActivity

class WifiTileService: TileService() {

    override fun onClick() {
        super.onClick()
        val intent = Intent(this, CaptivePortalLoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivityAndCollapse(intent)
    }
}