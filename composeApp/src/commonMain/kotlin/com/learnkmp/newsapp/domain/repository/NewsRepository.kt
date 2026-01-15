package com.learnkmp.newsapp.domain.repository

import com.learnkmp.newsapp.domain.model.Article
import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.model.Result

interface NewsRepository {
    suspend fun getNews(category: Category?): Result<List<Article>>
}
