package com.jeju.nanaland

import android.app.Application
import android.content.Context
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NanaLandApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Kakao SDK 초기화
        KakaoSdk.init(this, "bf7035e60f5541853f1e732e701fcc37")
    }
    init {
        instance = this
    }

    companion object {
        lateinit var instance: NanaLandApplication
        fun applicationContext(): Context = instance.applicationContext
    }
}