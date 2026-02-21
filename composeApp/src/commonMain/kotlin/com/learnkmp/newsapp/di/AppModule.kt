package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.database.AppDatabase
import com.learnkmp.newsapp.database.getRoomDatabase
import com.learnkmp.newsapp.networking.buildHttpClient
import com.learnkmp.newsapp.ui.ArticleViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val databaseModule = module {
    single { getRoomDatabase(get()) }
    single { get<AppDatabase>().articleDao() }
}

fun appModule() = module {
    includes(databaseModule)
    single<HttpClient> { buildHttpClient() }
    viewModelOf(::ArticleViewModel)
}