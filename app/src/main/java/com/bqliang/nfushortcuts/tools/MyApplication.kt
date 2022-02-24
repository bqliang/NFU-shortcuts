package com.bqliang.nfushortcuts.tools

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV

class MyApplication:Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        context = applicationContext
    }
}