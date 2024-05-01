package com.nanaland

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NanaLandApplication : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: NanaLandApplication
        fun applicationContext(): Context = instance.applicationContext
    }
}