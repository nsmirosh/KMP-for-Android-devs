package com.learnkmp.newsapp.utils

import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> MockRequestHandleScope.respondJson(
    content: T,
    status: HttpStatusCode = HttpStatusCode.OK,
    json: Json = Json
) = respond(
    content = json.encodeToString(content),
    status = status,
    headers = headersOf(HttpHeaders.ContentType, "application/json")
)
