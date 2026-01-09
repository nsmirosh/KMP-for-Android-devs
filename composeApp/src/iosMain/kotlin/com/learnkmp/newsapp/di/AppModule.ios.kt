package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.data.createIosDataStore
import org.koin.dsl.module

fun iosModule() = module {
    single { createIosDataStore() }
}