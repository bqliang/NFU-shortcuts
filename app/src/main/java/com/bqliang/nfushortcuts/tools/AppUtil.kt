package com.bqliang.nfushortcuts.tools

import android.content.pm.PackageManager

object AppUtil {

    private val packageManager : PackageManager by lazy {
        MyApplication.context.packageManager
    }

    fun isAppInstalled(packageName : String): Boolean {
        return try {
            packageManager.getApplicationInfo(packageName, 0).enabled
        }catch (e : PackageManager.NameNotFoundException){
            false
        }
    }
}