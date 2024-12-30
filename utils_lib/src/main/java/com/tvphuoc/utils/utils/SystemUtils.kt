package com.tvphuoc.utils.utils

import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import android.provider.Settings
import androidx.core.content.ContextCompat

/**
 * Checks if Wi-Fi is enabled on the device.
 *
 * @return `true` if Wi-Fi is enabled, `false` otherwise.
 */
fun isWifiEnable(context: Context): Boolean {
    val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    return wifiManager.isWifiEnabled
}

/**
 * Checks if location services (GPS) are enabled on the device.
 *
 * @return `true` if location services are enabled, `false` otherwise.
 */
fun isLocationEnable(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}

/**
 * Checks if mobile data is enabled on the device.
 *
 * This method uses ConnectivityManager to check for mobile data connectivity.
 *
 * @return `true` if mobile data is enabled, `false` otherwise.
 */
fun isMobileDataEnable(context: Context): Boolean {
    return Settings.Secure.getInt(context.contentResolver, "mobile_data", 1) == 1
}

/**
 * Checks if the device has an active network connection with internet capability.
 *
 * This method checks for any active network (Wi-Fi, mobile data, etc.) that has internet access.
 ** @return `true` if a network connection with internet access is available, `false` otherwise.
 */
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        ContextCompat.getSystemService(context, ConnectivityManager::class.java)
    var isConnected = false
    connectivityManager?.apply {
        activeNetwork?.let { getNetworkCapabilities(it) }
            ?.let {
                isConnected = it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            }
            ?: kotlin.run { isConnected = false }
    }
    return isConnected
}
