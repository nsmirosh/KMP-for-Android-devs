package com.learnkmp.newsapp.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.qualifier.named
import org.koin.dsl.module


fun iOSNetworkModule() = module {
    single<HttpClient>(named("platform")){ HttpClient(Darwin) }
}