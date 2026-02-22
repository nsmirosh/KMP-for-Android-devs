package com.learnkmp.newsapp.di

import org.koin.dsl.module
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.learnkmp.newsapp.data.createAndroidDataStore
import com.learnkmp.newsapp.database.getAndroidDatabaseBuilder
import org.koin.android.ext.koin.androidContext

actual fun platformModule() = module {
    single<DataStore<Preferences>> { createAndroidDataStore(androidContext()) }
    single { getAndroidDatabaseBuilder(androidContext()) }
}