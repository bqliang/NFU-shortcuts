package com.bqliang.nfushortcuts

import android.content.Intent
import android.service.quicksettings.TileService

class WifiTileService: TileService() {

    override fun onClick() {
        super.onClick()
        val intent = Intent(this, MyActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivityAndCollapse(intent)
    }
}