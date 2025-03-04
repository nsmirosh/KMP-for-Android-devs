package com.learnkmp.newsapp.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter as JavaDateTimeFormatter

actual fun formatDateTime(timestamp: String): String {

    val inputFormatter = JavaDateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val outputFormatter = JavaDateTimeFormatter.ofPattern("dd MMM, yyyy - HH:mm")

    val dateTime = LocalDateTime.parse(timestamp, inputFormatter)
    return dateTime.format(outputFormatter)
}