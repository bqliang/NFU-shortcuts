package com.bqliang.nfushortcuts.tools

import android.content.pm.PackageManager

object AppUtil {

    fun isAppInstalled(packageName : String): Boolean {
        return try {
            MyApplication.context.packageManager.getApplicationInfo(packageName, 0).enabled
        }catch (e : PackageManager.NameNotFoundException){
            false
        }
    }
}