package com.learnkmp.newsapp.models

data class Article(
    val id: Int,
    val author: String,
    val publishedAt: String,
    val title: String,
    val url: String,
)