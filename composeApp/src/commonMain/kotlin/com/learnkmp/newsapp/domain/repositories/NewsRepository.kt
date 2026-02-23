package com.learnkmp.newsapp.domain.repositories

import com.learnkmp.newsapp.domain.models.Article
import com.learnkmp.newsapp.domain.models.Category
import com.learnkmp.newsapp.domain.models.Result


interface NewsRepository {
    suspend fun getNewsData(category: Category?): Result<List<Article>>
}
