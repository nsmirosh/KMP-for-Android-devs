package com.learnkmp.newsapp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("article_id") val id: String = "",
    @SerialName("source_name") val source: String,
    val pubDate: String,
    val title: String,
    val link: String,
    @SerialName("image_url") val imageUrl: String? = null,
    val keywords: List<String> = emptyList(), //via coerceInputValues
)