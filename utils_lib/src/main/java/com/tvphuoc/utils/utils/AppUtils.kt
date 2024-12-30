package com.tvphuoc.utils.utils

import android.os.Build

/**
 * [Build.VERSION.SDK_INT] <= 29
 * @see <a href="https://apilevels.com/">Android API Levels</a>
 */
fun isAndroidQAndBelow() = Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q

/**
 * [Build.VERSION.SDK_INT] >= 26
 * @see <a href="https://apilevels.com/">Android API Levels</a>
 */
fun isAndroidOAndAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O //26

/**
 * [Build.VERSION.SDK_INT] >= 29
 * @see <a href="https://apilevels.com/">Android API Levels</a>
 */
fun isAndroidQAndAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q //29

/**
 * [Build.VERSION.SDK_INT] >= 30
 * @see <a href="https://apilevels.com/">Android API Levels</a>
 */
fun isAndroidRAndAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R //30

/**
 * [Build.VERSION.SDK_INT] >= 31
 * @see <a href="https://apilevels.com/">Android API Levels</a>
 */
fun isAndroidSAndAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S //31

/**
 * [Build.VERSION.SDK_INT] >= 33
 * @see <a href="https://apilevels.com/">Android API Levels</a>
 */
fun isAndroidTiramisuAndAbove() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU //33
