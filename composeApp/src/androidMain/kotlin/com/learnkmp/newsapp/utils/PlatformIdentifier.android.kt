package com.learnkmp.newsapp.utils

import java.util.UUID

actual fun getPlatformAndUniqueId(): String {
    return "Android-${UUID.randomUUID()}"
}

actual fun balls(): String {
    return "android ass"
}