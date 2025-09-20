package com.learnkmp.newsapp.networking

import com.learnkmp.newsapp.BuildKonfig
import com.learnkmp.newsapp.models.Article
import com.learnkmp.newsapp.models.ArticlesResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path


interface NewsDataRepo {
    suspend fun getNewsData(): List<Article>
}

class NewsDataRepoImpl : NewsDataRepo {
    val httpClient = createHttpClient()

    override suspend fun getNewsData(): List<Article> {

        val response: ArticlesResponse = httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "newsdata.io"
                path("api/1/latest")
                parameters.append("apikey", BuildKonfig.API_KEY)
            }
        }.body()

        return response.results
    }
}