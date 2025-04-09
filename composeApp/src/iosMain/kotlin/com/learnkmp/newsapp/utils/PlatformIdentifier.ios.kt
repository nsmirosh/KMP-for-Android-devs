package com.learnkmp.newsapp.utils

import platform.UIKit.UIDevice

actual fun getPlatformName(): String {
    return "iOS-${UIDevice.currentDevice.systemVersion}"
}