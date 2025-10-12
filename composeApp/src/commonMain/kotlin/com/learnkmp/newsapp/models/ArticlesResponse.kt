package com.learnkmp.newsapp.models

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponse(
   val status: String,
   val totalResults: Int,
   val results: List<Article>
)
