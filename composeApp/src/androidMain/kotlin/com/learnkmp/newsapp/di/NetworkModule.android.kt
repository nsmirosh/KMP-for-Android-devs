package com.learnkmp.newsapp.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.qualifier.named
import org.koin.dsl.module


fun androidNetworkModule() = module {
    single<HttpClient>(named("platform")) { HttpClient(OkHttp) }
}