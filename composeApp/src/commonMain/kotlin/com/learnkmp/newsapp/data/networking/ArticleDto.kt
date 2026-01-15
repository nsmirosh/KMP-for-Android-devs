package com.learnkmp.newsapp.data.networking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    @SerialName("article_id") val id: String = "",
    @SerialName("source_name") val source: String,
    val pubDate: String,
    val title: String,
    val link: String,
    val description: String? = null,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("source_icon") val sourceIconUrl: String? = null,
    val keywords: List<String> = emptyList(),
)
