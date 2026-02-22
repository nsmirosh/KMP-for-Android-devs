package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.database.getRoomDatabase
import com.learnkmp.newsapp.networking.buildHttpClient
import com.learnkmp.newsapp.ui.ArticleViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


fun appModule() = module {
    single { getRoomDatabase(get()).articleDao()  }
    single<HttpClient> { buildHttpClient() }
    single<HttpClient> { buildHttpClient() }
    viewModelOf(::ArticleViewModel)
}