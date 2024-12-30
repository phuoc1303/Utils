package com.tvphuoc.utils.extension

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import com.tvphuoc.utils.utils.isAndroidTiramisuAndAbove

inline fun <reified T : Parcelable> Bundle.parcelableFromBundle(key: String): T? =
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getParcelable(key, T::class.java)
        else ->
            @Suppress("DEPRECATION")
            getParcelable(key)
    }

inline fun <reified T : Parcelable> Intent.parcelableExtra(key: String): T? = when {
    isAndroidTiramisuAndAbove() -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}
