package com.learnkmp.newsapp.data.repository

import com.learnkmp.newsapp.BuildKonfig
import com.learnkmp.newsapp.data.database.ArticleDao
import com.learnkmp.newsapp.data.database.toDomain
import com.learnkmp.newsapp.data.database.toEntity
import com.learnkmp.newsapp.data.networking.ArticlesResponseDto
import com.learnkmp.newsapp.domain.model.Article
import com.learnkmp.newsapp.domain.model.Category
import com.learnkmp.newsapp.domain.model.Result
import com.learnkmp.newsapp.domain.repository.NewsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class NewsRepositoryImpl(
    private val httpClient: HttpClient,
    private val articleDao: ArticleDao
) : NewsRepository {

    override suspend fun getNews(category: Category?): Result<List<Article>> =
        try {
            val response: ArticlesResponseDto =
                httpClient.get("https://newsdata.io/api/1/latest") {
                    parameter("language", "en")
                    parameter("apikey", BuildKonfig.API_KEY)
                    parameter("category", category?.value)
                }.body()

            val articles = response.results

            // Cache articles
            if (category == null) {
                articleDao.deleteArticlesWithoutCategory()
            } else {
                articleDao.deleteArticlesByCategory(category.value)
            }
            articleDao.insertArticles(articles.map { it.toEntity(category?.value) })

            Result.Success(articles.map { it.toDomain() })
        } catch (e: Exception) {
            val cachedArticles = if (category == null) {
                articleDao.getArticlesWithoutCategory()
            } else {
                articleDao.getArticlesByCategory(category.value)
            }

            if (cachedArticles.isNotEmpty()) {
                Result.Success(cachedArticles.map { it.toDomain() })
            } else {
                Result.Error(e)
            }
        }
}
