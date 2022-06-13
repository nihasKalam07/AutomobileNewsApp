package com.nihaskalam.automobilenewsapp.ui

import android.content.Context
import android.widget.Toast
import es.dmoral.toasty.Toasty

enum class ToastType {
    SUCCESS, ERROR, NORMAL
}

fun Context.showToasts(
    msg: String,
    type: ToastType = ToastType.NORMAL,
    length: Int = Toast.LENGTH_SHORT
) {

    when (type) {
        ToastType.SUCCESS -> {
            Toasty.success(this, msg, Toast.LENGTH_SHORT, true).show()
        }
        ToastType.ERROR -> {
            Toasty.error(this, msg, Toast.LENGTH_SHORT, true).show()
        }
        ToastType.NORMAL -> {
            Toasty.normal(this, msg).show()
        }
    }
}