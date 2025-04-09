package com.learnkmp.newsapp.utils

import platform.Foundation.*

actual fun formatDateTime(timestamp: String): String {
    val inputFormatter = NSDateFormatter().apply {
        dateFormat = "yyyy-MM-dd HH:mm:ss"
        timeZone = NSTimeZone.localTimeZone
    }

    val outputFormatter = NSDateFormatter().apply {
        dateFormat = "dd MMM, yyyy - HH:mm"
        timeZone = NSTimeZone.localTimeZone
    }

    val date = inputFormatter.dateFromString(timestamp) ?: return timestamp
    return outputFormatter.stringFromDate(date)
}