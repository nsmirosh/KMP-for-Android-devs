package com.learnkmp.newsapp.networking

import com.learnkmp.newsapp.BuildKonfig
import com.learnkmp.newsapp.models.Article
import com.learnkmp.newsapp.models.ArticlesResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import com.learnkmp.newsapp.domain.Result


interface NewsDataRepo {
    suspend fun getNewsData(): Result<List<Article>>
}

class NewsDataRepoImpl : NewsDataRepo {

    val httpClient = buildHttpClient()

    override suspend fun getNewsData(): Result<List<Article>> =
        try {
            val response: ArticlesResponse =
                httpClient.get("https://newsdata.io/api/1/latest?apikey=${BuildKonfig.API_KEY}")
                    .body()
            Result.Success(response.results)
        } catch (e: Exception) {
            Result.Error(e)
        }
}