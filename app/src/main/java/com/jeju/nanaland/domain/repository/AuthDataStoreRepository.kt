package com.jeju.nanaland.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthDataStoreRepository {

    // Access Token 가져오기
    suspend fun getAccessToken(): Flow<String?>

    // Refresh Token 가져오기
    fun getRefreshToken(): Flow<String?>

    // Access Token 저장하기
    suspend fun saveAccessToken(accessToken: String)

    // Refresh Token 저장하기
    suspend fun saveRefreshToken(refreshToken: String)

    suspend fun clearAll()
}