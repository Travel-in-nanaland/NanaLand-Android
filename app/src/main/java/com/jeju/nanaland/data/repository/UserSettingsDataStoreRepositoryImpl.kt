package com.jeju.nanaland.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository
import com.jeju.nanaland.globalvalue.constant.KEY_LANGUAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserSettingsDataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : UserSettingsDataStoreRepository {

    // 언어 설정 가져오기
    override fun getLanguage(): Flow<String?> {
        val key = stringPreferencesKey(KEY_LANGUAGE)
        return dataStore.data.map { it[key] }
    }

    // 언어 설정 저장하기
    override suspend fun saveLanguage(language: String) {
        val key = stringPreferencesKey(KEY_LANGUAGE)
        dataStore.edit { it[key] = language }
    }
}