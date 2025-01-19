package com.learnkmp.newsapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
actual fun formatCurrentTimestamp(timeStamp: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a", Locale.getDefault())
    val dateTime = LocalDateTime.parse(timeStamp, inputFormatter)
    return dateTime.format(outputFormatter)
}