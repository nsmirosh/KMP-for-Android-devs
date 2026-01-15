package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.data.datastore.createIosDataStore
import com.learnkmp.newsapp.data.database.getIOSDatabaseBuilder
import org.koin.dsl.module

fun iosModule() = module {
    single { createIosDataStore() }
    single { getIOSDatabaseBuilder() }
}