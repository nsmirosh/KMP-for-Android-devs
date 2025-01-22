package com.learnkmp.newsapp.utils

import platform.Foundation.NSUUID

actual fun getPlatformAndUniqueId(): String {
    return "iOS-${NSUUID().UUIDString}"
}