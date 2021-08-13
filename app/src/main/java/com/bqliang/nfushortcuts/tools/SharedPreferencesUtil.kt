package com.bqliang.nfushortcuts.tools

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtil {

    private val sp : SharedPreferences by lazy {
        MyApplication.context.getSharedPreferences(
            "app_data",
            Context.MODE_PRIVATE
        )
    }

    fun getString(key: String, defValue: String?) =
        sp.getString(key, defValue)

    fun saveString(key: String, value: String?){
        sp.edit()
            .putString(key, value)
            .commit()
    }
}