package com.learnkmp.newsapp.networking

import com.learnkmp.newsapp.models.Article
import io.ktor.client.request.get


interface NewsDataRepo {
    suspend fun getNewsData(): List<Article>
}

class NewsDataRepoImpl : NewsDataRepo {

    val httpClient = buildHttpClient()

    override suspend fun getNewsData(): List<Article> {
        //TODO provide the API key via BuildKonfig

        httpClient.get("https://newsdata.io/api/1/latest?apikey=")
        return emptyList()
    }
}