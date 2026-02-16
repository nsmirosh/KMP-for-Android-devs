package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.data.createIosStore
import org.koin.dsl.module

actual fun platformModule() = module {
    single { createIosStore() }
}