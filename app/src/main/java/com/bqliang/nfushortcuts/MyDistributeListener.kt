package com.bqliang.nfushortcuts

import android.app.Activity
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.microsoft.appcenter.distribute.Distribute
import com.microsoft.appcenter.distribute.DistributeListener
import com.microsoft.appcenter.distribute.ReleaseDetails
import com.microsoft.appcenter.distribute.UpdateAction

class MyDistributeListener : DistributeListener {
    override fun onReleaseAvailable(activity: Activity, releaseDetails: ReleaseDetails): Boolean {
        val versionName = releaseDetails.shortVersion
        val versionCode = releaseDetails.version
        val releaseNotes = releaseDetails.releaseNotes
        val releaseNotesUrl = releaseDetails.releaseNotesUrl

//        MaterialAlertDialogBuilder(activity)
//            .setTitle("Version $versionName available!")
//            .setMessage("")
//            .setPositiveButton(""){ dialog, which ->
//                Distribute.notifyUpdateAction(UpdateAction.UPDATE)
//            }
//            .setNegativeButton(""){ dialog, which ->
//                Distribute.notifyUpdateAction(UpdateAction.POSTPONE)
//            }
//            .setCancelable(false)
//            .create()
//            .show()
        return false
    }

    override fun onNoReleaseAvailable(activity: Activity?) {
        R.string.no_update_available.showToast()
    }
}