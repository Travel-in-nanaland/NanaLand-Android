package com.jeju.nanaland.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jeju.nanaland.domain.repository.AuthDataStoreRepository
import com.jeju.nanaland.globalvalue.constant.KEY_ACCESS_TOKEN
import com.jeju.nanaland.globalvalue.constant.KEY_REFRESH_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthDataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : AuthDataStoreRepository {

    // DataStore에서 Access Token 가져오기
    override suspend fun getAccessToken(): Flow<String?> {
        val key = stringPreferencesKey(KEY_ACCESS_TOKEN)
        return dataStore.data.map { it[key] }
    }

    // DataStore에서 Refresh Token 가져오기
    override fun getRefreshToken(): Flow<String?> {
        val key = stringPreferencesKey(KEY_REFRESH_TOKEN)
        return dataStore.data.map { it[key] }
    }

    // DataStore에 Access Token 저장하기
    override suspend fun saveAccessToken(accessToken: String) {
        val key = stringPreferencesKey(KEY_ACCESS_TOKEN)
        dataStore.edit { it[key] = accessToken }
    }

    // DataStore에 Refresh Token 저장하기
    override suspend fun saveRefreshToken(refreshToken: String) {
        val key = stringPreferencesKey(KEY_REFRESH_TOKEN)
        dataStore.edit { it[key] = refreshToken }
    }

    override suspend fun clearAll() {
        dataStore.edit {
            it.clear()
        }
    }
}