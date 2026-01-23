package com.learnkmp.newsapp.di

import org.koin.dsl.module

actual fun platformSpecificModule() = module {
    includes(
        androidAppModule(),
        androidNetworkModule()
    )
}