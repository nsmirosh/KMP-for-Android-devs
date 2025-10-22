package com.learnkmp.newsapp.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp


//TODO define your Android actual here

actual fun createHttpClient(): HttpClient  = HttpClient(OkHttp)
