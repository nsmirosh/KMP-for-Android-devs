package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.networking.NewsDataRepo
import com.learnkmp.newsapp.networking.NewsDataRepoImpl
import com.learnkmp.newsapp.networking.buildHttpClient
import com.learnkmp.newsapp.ui.ArticleViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    single<HttpClient> { buildHttpClient() }
    viewModelOf(::ArticleViewModel)
    singleOf(::NewsDataRepoImpl) { bind<NewsDataRepo>() }
}