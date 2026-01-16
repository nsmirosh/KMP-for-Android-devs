package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.data.database.AppDatabase
import com.learnkmp.newsapp.data.database.getRoomDatabase
import com.learnkmp.newsapp.data.repository.NewsRepositoryImpl
import com.learnkmp.newsapp.data.repository.SettingsRepositoryImpl
import com.learnkmp.newsapp.domain.repository.NewsRepository
import com.learnkmp.newsapp.domain.repository.SettingsRepository
import com.learnkmp.newsapp.domain.usecase.GetArticlesUseCase
import com.learnkmp.newsapp.domain.usecase.GetSelectedCategoryUseCase
import com.learnkmp.newsapp.domain.usecase.SaveSelectedCategoryUseCase
import com.learnkmp.newsapp.ui.viewmodel.ArticleViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val databaseModule = module {
    single { getRoomDatabase(get()) }
    single { get<AppDatabase>().articleDao() }
}

val repositoryModule = module {
    singleOf(::NewsRepositoryImpl) { bind<NewsRepository>() }
    singleOf(::SettingsRepositoryImpl) { bind<SettingsRepository>() }
}

val useCaseModule = module {
    factoryOf(::GetArticlesUseCase)
    factoryOf(::GetSelectedCategoryUseCase)
    factoryOf(::SaveSelectedCategoryUseCase)
}

fun appModule() = module {
    includes(databaseModule, repositoryModule, useCaseModule)
    viewModelOf(::ArticleViewModel)
}