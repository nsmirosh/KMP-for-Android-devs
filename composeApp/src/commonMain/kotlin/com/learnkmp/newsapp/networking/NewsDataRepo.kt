package com.learnkmp.newsapp.networking

import com.learnkmp.newsapp.BuildKonfig
import com.learnkmp.newsapp.models.Article
import io.ktor.client.request.get


interface NewsDataRepo {
    suspend fun getNewsData(): List<Article>
}

class NewsDataRepoImpl : NewsDataRepo {

    val httpClient = buildHttpClient()

    override suspend fun getNewsData(): List<Article> {
        httpClient.get("https://newsdata.io/api/1/latest?apikey=${BuildKonfig.API_KEY}")
        return emptyList()
    }
}