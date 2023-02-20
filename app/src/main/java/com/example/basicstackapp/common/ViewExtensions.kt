package com.example.basicstackapp.common

import android.view.View

fun View.showHide(isShow: Boolean) {
    this.visibility = when (isShow) {
        true -> View.VISIBLE
        else -> View.GONE
    }
}

fun View.visibleIfTrue(flag: Boolean) {
    this.visibility = when (flag) {
        true -> View.VISIBLE
        else -> View.GONE
    }
}
