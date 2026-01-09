package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.ui.ArticleViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


fun appModule() = module {
    viewModelOf(::ArticleViewModel)
}