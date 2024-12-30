package com.tvphuoc.utils.extension

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


fun Context.hasSelfPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Context.hasSelfPermissions(vararg permissions: String): Boolean {
    permissions.forEach {
        if (!hasSelfPermission(it)) return false
    }
    return true
}

internal fun Activity.shouldShowRequestPermissionsRationale(vararg permissions: String): Boolean {
    permissions.forEach {
        if (shouldShowRequestPermissionRationale(it)) return true
    }

    return false
}
