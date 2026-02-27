package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.data.repositories.NewsRepositoryImpl
import com.learnkmp.newsapp.data.repositories.SettingsRepositoryImpl
import com.learnkmp.newsapp.database.getRoomDatabase
import com.learnkmp.newsapp.domain.repositories.NewsRepository
import com.learnkmp.newsapp.domain.repositories.SettingsRepository
import com.learnkmp.newsapp.networking.buildHttpClient
import com.learnkmp.newsapp.ui.ArticleViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> { buildHttpClient() }
}

val repositoryModule = module {
    singleOf(::NewsRepositoryImpl) { bind<NewsRepository>() }
    singleOf(::SettingsRepositoryImpl) { bind<SettingsRepository>() }
}

fun sharedModule() = module {
    includes( repositoryModule, networkModule)
    viewModelOf(::ArticleViewModel)
}
