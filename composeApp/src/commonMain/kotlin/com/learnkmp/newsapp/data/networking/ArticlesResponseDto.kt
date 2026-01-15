package com.learnkmp.newsapp.data.networking

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponseDto(
   val status: String,
   val totalResults: Int,
   val results: List<ArticleDto>
)
