package com.tvphuoc.utils.utils

import android.content.Context
import android.media.AudioManager
import androidx.core.content.ContextCompat

object VolumeUtils {


    /**
     * Retrieves the minimum volume for a given audio stream type.
     *
     * This function uses the AudioManager to get the minimum volume for the specified stream type.
     * On Android P (API level 28) and above, it uses `getStreamMinVolume()`.
     * On older versions, it returns 0.
     *
     * @param context The application context.
     * @param streamType The audio stream type (e.g., AudioManager.STREAM_MUSIC, AudioManager.STREAM_RING).
     * @return The minimum volume for the specified stream type, or 0 if not supported or an error occurs.
     */
    fun getMinVolume(context: Context, streamType: Int): Int {
        if (isAndroidPAndAbove()) {
            val am = ContextCompat.getSystemService(context, AudioManager::class.java)
            am?.let {
                return it.getStreamMinVolume(streamType)
            }
        }
        return 0
    }

    fun getMaxVolume(context: Context, streamType: Int): Int {
        val am = ContextCompat.getSystemService(context, AudioManager::class.java)
        return am?.getStreamMaxVolume(streamType) ?: 0
    }

    fun getVolume(context: Context, streamType: Int): Int {
        val am = ContextCompat.getSystemService(context, AudioManager::class.java)
        return am?.getStreamVolume(streamType) ?: 0
    }

    fun setVolume(context: Context, streamType: Int, volume: Int, flag: Int = 0) {
        val am = ContextCompat.getSystemService(context, AudioManager::class.java)
        try {
            am?.setStreamVolume(streamType, volume, flag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
