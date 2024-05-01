package com.nanaland.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesDataStoreRepository {

    // DataStore에서 Access Token 가져오기
    suspend fun getAccessToken(): Flow<String?>

    // DataStore에서 Refresh Token 가져오기
    suspend fun getRefreshToken(): Flow<String?>

    // DataStore에 Access Token 저장하기
    suspend fun saveAccessToken(accessToken: String)

    // DataStore에 Refresh Token 저장하기
    suspend fun saveRefreshToken(refreshToken: String)
}