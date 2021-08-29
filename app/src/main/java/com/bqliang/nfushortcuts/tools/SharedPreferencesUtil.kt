package com.bqliang.nfushortcuts.tools

import android.content.Context
import android.content.SharedPreferences
import com.microsoft.appcenter.analytics.Analytics

object SharedPreferencesUtil {

    private val sp : SharedPreferences by lazy {
        MyApplication.context.getSharedPreferences(
            "app_data",
            Context.MODE_PRIVATE
        )
    }

    fun getString(key: String, defValue: String?) = sp.getString(key, defValue)

    fun saveString(key: String, value: String?){
        sp.edit()
            .putString(key, value)
            .commit()
    }

    fun getBoolean(key: String, defValue: Boolean) = sp.getBoolean(key,defValue)

    fun saveBoolean(key: String, value: Boolean){
        sp.edit()
            .putBoolean(key, value)
            .commit()
    }

    fun getInt(key: String, defValue: Int) = sp.getInt(key, defValue)

    fun saveInt(key: String, value: Int){
        sp.edit()
            .putInt(key, value)
            .commit()
    }
}