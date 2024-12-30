package com.tvphuoc.utils.extension

import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams


fun View.setMarginTop(value: Int) = updateLayoutParams<ViewGroup.MarginLayoutParams> {
    topMargin = value
}

fun View.setMarginBottom(value: Int) = updateLayoutParams<ViewGroup.MarginLayoutParams> {
    bottomMargin = value
}

fun View.setMarginEnd(value: Int) {
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        marginEnd = value
    }
}

fun View.setMarginHorizontal(value: Int) = updateLayoutParams<ViewGroup.MarginLayoutParams> {
    leftMargin = value
    rightMargin = value
}

fun View.setPaddingTop(value: Int) {
    this.setPadding(
        paddingLeft, value, paddingRight, paddingBottom
    )
}
