package com.jeju.nanaland

import android.app.Application
import android.content.Context
import com.getkeepsafe.relinker.MissingLibraryException
import com.kakao.sdk.common.KakaoSdk
import com.kakao.vectormap.KakaoMapSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NanaLandApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Kakao SDK 초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_KEY)
        try {
            KakaoMapSdk.init(this, BuildConfig.KAKAO_KEY)
        } catch (e: MissingLibraryException) {

        }
    }
    init {
        instance = this
    }

    companion object {
        lateinit var instance: NanaLandApplication
        fun applicationContext(): Context = instance.applicationContext
    }
}