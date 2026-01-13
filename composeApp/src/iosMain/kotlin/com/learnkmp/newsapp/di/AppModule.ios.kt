package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.data.createIosDataStore
import com.learnkmp.newsapp.database.getIOSDatabaseBuilder
import org.koin.dsl.module

fun iosModule() = module {
    single { createIosDataStore() }
    single { getIOSDatabaseBuilder() }
}