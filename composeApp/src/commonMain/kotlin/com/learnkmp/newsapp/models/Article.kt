package com.learnkmp.newsapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: String = "",
    val source: String,
    val pubDate: String,
    val title: String,
    val link: String,
    val imageUrl: String? = null,
    val keywords: List<String> = emptyList(),
)