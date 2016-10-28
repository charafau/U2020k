package com.nullpointerbay.u2020k.extension


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun Int.dpToPx(context: Context) = this * context.resources.displayMetrics.density

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}