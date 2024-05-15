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
        KakaoSdk.init(this, "319c262a291562c6993e381b90508e20")
    }
    init {
        instance = this
    }

    companion object {
        lateinit var instance: NanaLandApplication
        fun applicationContext(): Context = instance.applicationContext
    }
}