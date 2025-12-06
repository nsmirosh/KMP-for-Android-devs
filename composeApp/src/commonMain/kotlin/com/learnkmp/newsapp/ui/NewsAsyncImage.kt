package com.learnkmp.newsapp.ui

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage


@Composable
fun NewsAsyncImage(url: String?, modifier: Modifier = Modifier) {
    val randomIndex = (0 until pastelColors.size).random()
    val tint = pastelColors[randomIndex]
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
            .background(tint),
        contentScale = ContentScale.Crop,
        placeholder = ColorPainter(tint),
        error = ColorPainter(Color.Black)
    )
}
private val pastelColors = listOf(
    Color(0xFFE3F2FD), // light blue
    Color(0xFFFFF3E0), // light orange
    Color(0xFFE8F5E9), // light green
    Color(0xFFF3E5F5), // light purple
    Color(0xFFFFEBEE), // light red
    Color(0xFFE0F2F1), // teal
    Color(0xFFFFFDE7), // yellow
    Color(0xFFECEFF1)  // blue grey
)
