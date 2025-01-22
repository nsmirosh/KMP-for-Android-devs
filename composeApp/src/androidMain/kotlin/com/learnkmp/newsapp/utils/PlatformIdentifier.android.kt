package com.learnkmp.newsapp.utils

import java.util.UUID

actual fun getPlatformAndUniqueId(): String {
    return "Android-${UUID.randomUUID()}"
}