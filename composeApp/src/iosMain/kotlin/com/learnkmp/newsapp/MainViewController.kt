package com.learnkmp.newsapp

import androidx.compose.ui.window.ComposeUIViewController
import com.learnkmp.newsapp.di.iosModule
import com.learnkmp.newsapp.ui.App

fun MainViewController() = ComposeUIViewController { App(iosModule) }