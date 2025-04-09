package com.learnkmp.newsapp.utils

import android.os.Build

actual fun getPlatformName(): String {
    return "Android-${Build.VERSION.RELEASE}"
}