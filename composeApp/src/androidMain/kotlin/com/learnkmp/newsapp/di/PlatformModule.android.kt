package com.learnkmp.newsapp.di

import com.learnkmp.newsapp.data.createAndroidDataStore
import com.learnkmp.newsapp.database.getAndroidDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun platformSpecificModule() = module {
    single { createAndroidDataStore(androidContext()) }
}