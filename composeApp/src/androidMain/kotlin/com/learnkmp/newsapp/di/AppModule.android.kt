package com.learnkmp.newsapp.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.learnkmp.newsapp.data.createAndroidDataStore
import com.learnkmp.newsapp.database.getAndroidDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


fun androidAppModule() = module {
    single<DataStore<Preferences>> { createAndroidDataStore(androidContext()) }
    single { getAndroidDatabaseBuilder(androidContext()) }
}