package com.learnkmp.newsapp.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual fun getPlatformHttpClient(): HttpClient {
    return HttpClient(Darwin)
}