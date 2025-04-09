package com.learnkmp.newsapp.utils

import java.util.UUID

actual fun generateClickId(): String {
    return "${UUID.randomUUID()}"
}