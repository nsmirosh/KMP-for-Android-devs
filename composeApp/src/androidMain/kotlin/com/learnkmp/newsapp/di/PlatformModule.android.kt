package com.learnkmp.newsapp.di

import org.koin.dsl.module

actual fun platformModule() = module {
    includes(
        androidAppModule(),
        androidNetworkModule()
    )
}