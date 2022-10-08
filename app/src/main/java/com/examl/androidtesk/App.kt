package com.examl.androidtesk

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// This is Init Class.
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}
