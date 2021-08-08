package com.bqliang.nfushortcuts.tools

import android.content.Context
import android.content.SharedPreferences
import kotlin.concurrent.thread

fun getSharedPreferences(): SharedPreferences = MyApplication.context
    .getSharedPreferences("app_data", Context.MODE_PRIVATE)

fun getStringFromSharedPreferences(key :String, defValue:String?) =
    getSharedPreferences().getString(key, defValue)
