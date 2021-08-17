package com.bqliang.nfushortcuts.tools

import java.time.LocalDateTime

object TimeUtil {
    val now : String
    get() = LocalDateTime.now()
        .toString()
        .dropLast(10)
        .replace("T", " ")
}