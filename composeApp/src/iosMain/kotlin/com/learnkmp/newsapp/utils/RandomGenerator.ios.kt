package com.learnkmp.newsapp.utils

import platform.Foundation.NSUUID.Companion.UUID


actual fun generateClickId(): String {
    return UUID().UUIDString
}