package com.learnkmp.newsapp.domain.model

data class Article(
    val id: String,
    val source: String,
    val pubDate: String,
    val title: String,
    val link: String,
    val description: String?,
    val imageUrl: String?,
    val sourceIconUrl: String?,
    val keywords: List<String>
)
