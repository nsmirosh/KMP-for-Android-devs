package com.learnkmp.newsapp.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

actual fun getPlatformHttpClient(): HttpClient  = HttpClient(OkHttp)

