package com.example.mycinema

import android.app.Application
import timber.log.Timber

class MyCinema : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}