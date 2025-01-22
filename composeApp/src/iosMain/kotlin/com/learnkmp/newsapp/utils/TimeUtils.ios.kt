package com.learnkmp.newsapp.utils
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone
import platform.Foundation.currentLocale
import platform.Foundation.localTimeZone

actual fun formatCurrentTimeStamp(timeStamp: String): String {
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
    val outputFormatter = NSDateFormatter()
    outputFormatter.dateFormat = "MMM dd, yyyy hh:mm a"
    return outputFormatter.stringFromDate(date)
}
