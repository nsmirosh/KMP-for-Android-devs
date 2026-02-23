package com.learnkmp.newsapp.data.repositories

import com.learnkmp.newsapp.BuildKonfig
import com.learnkmp.newsapp.domain.models.Article
import com.learnkmp.newsapp.domain.models.ArticlesResponse
import com.learnkmp.newsapp.domain.models.Category
import com.learnkmp.newsapp.domain.models.Result
import com.learnkmp.newsapp.domain.repositories.NewsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter


class NewsRepositoryImpl(
    private val httpClient: HttpClient,
) : NewsRepository {

    override suspend fun getNewsData(category: Category?): Result<List<Article>> =
        try {
            val response: ArticlesResponse =
                httpClient.get("https://newsdata.io/api/1/latest") {
                    parameter("language", "en")
                    parameter("apikey", BuildKonfig.API_KEY)
                    parameter("category", category?.value)
                }.body()
            Result.Success(response.results)
        } catch (e: Exception) {
            Result.Error(e)
        }
}