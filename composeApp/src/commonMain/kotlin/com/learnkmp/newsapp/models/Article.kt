package com.learnkmp.newsapp.models

import kotlinx.serialization.SerialName

/**
 * Represents a news article for the feed UI.
 * imageUrl: optional image URL coming with the article.
 * keywords: optional list of keywords/tags for the article.
 */
data class Article(
    val id: Int,
    @SerialName("source_name") val source: String,
    val pubDate: String,
    val title: String,
    val link: String,
    @SerialName("image_url") val imageUrl: String? = null,
    val keywords: List<String> = emptyList(),
)