package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.data.createIosStore
import org.koin.dsl.module

val iosModule = module {
    single { createIosStore() }
}