package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.networking.NewsDataRepo
import com.learnkmp.newsapp.networking.NewsDataRepoImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module


fun iOSNetworkModule() = module {
    single<HttpClient> {
        // injecting platform-specific HttpClient here
        val platformHttpClient: HttpClient = get(named("platform"))
        platformHttpClient.config {
            val formatter = Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
                coerceInputValues = true // for empty keywords
            }
            install(ContentNegotiation) {
                json(formatter)
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.HEADERS
            }
            HttpResponseValidator {
                validateResponse { response ->
                    val text = response.bodyAsText()
                    println("BODY")
                    if (text.startsWith("{")) {
                        println(
                            formatter.encodeToString(
                                JsonElement.serializer(),
                                Json.parseToJsonElement(text)
                            )
                        )
                    }
                }
            }
        }


    }
    singleOf(::NewsDataRepoImpl) { bind<NewsDataRepo>() }
}