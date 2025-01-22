package com.learnkmp.newsapp.utils
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale



@RequiresApi(Build.VERSION_CODES.O)
actual fun formatCurrentTimeStamp(timeStamp: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a", Locale.getDefault())

    val dateTime = try {
        LocalDateTime.parse(timeStamp, inputFormatter)
    } catch (
        e: Exception
    ) {
        e.printStackTrace()
        LocalDateTime.now()
    }
    return dateTime.format(outputFormatter)
}
