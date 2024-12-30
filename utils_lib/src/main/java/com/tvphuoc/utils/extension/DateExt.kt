package com.tvphuoc.utils.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatCurrentLocate(pattern: String): String {
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return simpleDateFormat.format(this)
}
