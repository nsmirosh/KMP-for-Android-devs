package com.learnkmp.newsapp.networking

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement


expect fun createHttpClient(): HttpClient

@OptIn(ExperimentalSerializationApi::class)
fun buildHttpClient() =
    createHttpClient().config {
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

