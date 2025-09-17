package com.learnkmp.newsapp.models

/**
 * Represents a news article for the feed UI.
 * imageUrl: optional image URL coming with the article.
 * keywords: optional list of keywords/tags for the article.
 */
data class Article(
    val id: Int,
    val author: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val imageUrl: String? = null,
    val keywords: List<String> = emptyList(),
)