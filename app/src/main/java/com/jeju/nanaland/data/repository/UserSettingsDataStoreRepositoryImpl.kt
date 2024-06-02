package com.jeju.nanaland.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jeju.nanaland.domain.repository.UserSettingsDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserSettingsDataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : UserSettingsDataStoreRepository {

    override fun getValue(key: String): Flow<String?> {
        val prefKey = stringPreferencesKey(key)
        return dataStore.data.map { it[prefKey] }
    }

    override suspend fun saveValue(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        dataStore.edit { it[prefKey] = value }
    }

    override suspend fun clearAll() {
        dataStore.edit {
            it.clear()
        }
    }
}