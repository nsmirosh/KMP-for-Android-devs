package com.learnkmp.newsapp.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin


//TODO define your iOS actual here
actual fun createHttpClient(): HttpClient  = HttpClient(Darwin)
