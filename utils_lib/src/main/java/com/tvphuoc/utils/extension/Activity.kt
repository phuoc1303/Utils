package com.tvphuoc.utils.extension

import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.setOnBackPress(callback: () -> Unit) {
    onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            callback.invoke()
        }
    })
}
