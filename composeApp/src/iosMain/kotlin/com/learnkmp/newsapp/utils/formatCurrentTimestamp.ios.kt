package com.learnkmp.newsapp.utils
// shared/src/iosMain/kotlin/TimestampFormatter.kt

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone
import platform.Foundation.currentLocale
import platform.Foundation.localTimeZone

actual fun formatCurrentTimestamp(timeStamp: String): String {
    val inputFormmater = NSDateFormatter()
    inputFormmater.dateFormat = "yyyy-MM-dd HH:mm:ss"
    inputFormmater.locale = NSLocale.currentLocale()
    inputFormmater.timeZone = NSTimeZone.localTimeZone()
    val date: NSDate = try {
        inputFormmater.dateFromString(timeStamp)!!
    } catch (e: Exception) {
        println("Error: $e")
        NSDate()
    }
    //TODO add missing date output formatter
    val outputFormatter = NSDateFormatter()
    outputFormatter.dateFormat = "MMM dd, yyyy hh:mm a"
    return outputFormatter.stringFromDate(date)
}
