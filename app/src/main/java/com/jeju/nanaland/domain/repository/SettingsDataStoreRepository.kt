package com.jeju.nanaland.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsDataStoreRepository {

    // 언어 설정 가져오기
    suspend fun getLanguage(): Flow<String?>

    // 언어 설정 저장하기
    suspend fun saveLanguage(language: String)
}