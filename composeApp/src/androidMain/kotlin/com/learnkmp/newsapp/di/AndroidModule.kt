package com.learnkmp.newsapp.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.learnkmp.newsapp.data.createAndroidStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val androidModule = module {
    single<DataStore<Preferences>> { createAndroidStore(androidContext()) }
}