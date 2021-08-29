package com.bqliang.nfushortcuts

import android.app.Activity
import com.bqliang.nfushortcuts.tools.showToast
import com.microsoft.appcenter.distribute.DistributeListener
import com.microsoft.appcenter.distribute.ReleaseDetails

class MyDistributeListener : DistributeListener {
    override fun onReleaseAvailable(activity: Activity?, releaseDetails: ReleaseDetails?): Boolean {
        "onReleaseAvailable".showToast()
        return false
    }

    override fun onNoReleaseAvailable(activity: Activity?) {
        "onNoReleaseAvailable".showToast()
    }
}