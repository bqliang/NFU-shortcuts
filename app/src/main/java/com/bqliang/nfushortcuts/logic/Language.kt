package com.bqliang.nfushortcuts.logic

import com.bqliang.nfushortcuts.tools.MyApplication

object Language {

    fun get(): String {
        val language = MyApplication.context.resources.configuration.locales[0].toLanguageTag()
        return if (language.startsWith("en")) "en_US" else "zh_CN"
    }
}