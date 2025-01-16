package com.learnkmp.newsapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform