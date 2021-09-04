package com.bqliang.nfushortcuts.listener

import android.app.Activity
import com.bqliang.nfushortcuts.R
import com.bqliang.nfushortcuts.tools.showToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.microsoft.appcenter.distribute.Distribute
import com.microsoft.appcenter.distribute.DistributeListener
import com.microsoft.appcenter.distribute.ReleaseDetails
import com.microsoft.appcenter.distribute.UpdateAction

class MyDistributeListener : DistributeListener {
    override fun onReleaseAvailable(activity: Activity, releaseDetails: ReleaseDetails): Boolean {
        val size = releaseDetails.size /(1024 * 1024)
        val versionName = releaseDetails.shortVersion
        val releaseNotes = releaseDetails.releaseNotes

        MaterialAlertDialogBuilder(activity)
            .setTitle(R.string.update_available)
            .setMessage(
                activity.getString(R.string.new_version) + versionName + "\n"
            + activity.getString(R.string.size) + size + "M\n"
            + activity.getString(R.string.what_is_new) + "\n" + releaseNotes
            )
            .setPositiveButton(R.string.download){ _, _ ->
                Distribute.notifyUpdateAction(UpdateAction.UPDATE)
            }
            .setNegativeButton(R.string.ask_me_in_a_day){ _, _ ->
                Distribute.notifyUpdateAction(UpdateAction.POSTPONE)
            }
            .setCancelable(true)
            .create()
            .show()
        return true
    }

    override fun onNoReleaseAvailable(activity: Activity?) {
        R.string.no_update_available.showToast()
    }
}