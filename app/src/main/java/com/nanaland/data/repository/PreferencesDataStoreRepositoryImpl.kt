package com.nanaland.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.nanaland.domain.repository.MemberRepository
import com.nanaland.domain.repository.PreferencesDataStoreRepository
import com.nanaland.globalvalue.constant.KEY_ACCESS_TOKEN
import com.nanaland.globalvalue.constant.KEY_REFRESH_TOKEN
import com.nanaland.util.network.NetworkResultHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesDataStoreRepositoryImpl(
    private val preferencesDataStore: DataStore<Preferences>
) : PreferencesDataStoreRepository, NetworkResultHandler {

    // DataStore에서 Access Token 가져오기
    override suspend fun getAccessToken(): Flow<String?> {
        val key = stringPreferencesKey(KEY_ACCESS_TOKEN)
        return preferencesDataStore.data.map {
            it[key]
        }
    }

    // DataStore에서 Refresh Token 가져오기
    override suspend fun getRefreshToken(): Flow<String?> {
        val key = stringPreferencesKey(KEY_REFRESH_TOKEN)
        return preferencesDataStore.data.map {
            it[key]
        }
    }

    // DataStore에 Access Token 저장하기
    override suspend fun saveAccessToken(accessToken: String) {
        val key = stringPreferencesKey(KEY_ACCESS_TOKEN)
        preferencesDataStore.edit {
            it[key] = accessToken
        }
    }

    // DataStore에 Refresh Token 저장하기
    override suspend fun saveRefreshToken(refreshToken: String) {
        val key = stringPreferencesKey(KEY_REFRESH_TOKEN)
        preferencesDataStore.edit {
            it[key] = refreshToken
        }
    }
}