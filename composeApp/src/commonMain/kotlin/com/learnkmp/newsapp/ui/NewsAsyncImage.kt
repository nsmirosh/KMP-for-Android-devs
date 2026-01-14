package com.learnkmp.newsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage


@Composable
fun NewsAsyncImage(url: String?, modifier: Modifier = Modifier) {
    val randomIndex = (0 until pastelColors.size).random()
    val tint = pastelColors[randomIndex]

    if (url == null) {
        //TODO uncomment once you import the resources and the images
//        Image(
//            painter = painterResource(Res.drawable.image_placeholder),
//            contentDescription = null,
//            modifier = modifier.padding(16.dp).background(tint),
//        )
        return
    }
    SubcomposeAsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
            .background(tint),
        contentScale = ContentScale.Crop,
        loading = {

            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).size(32.dp))
            }
        },
        error = {
            //TODO uncomment once you import the resources and the images
//        Image(
//            painter = painterResource(Res.drawable.image_load_fail),
//            contentDescription = null,
//            modifier = modifier.padding(16.dp).background(tint),
//        )
        }
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
