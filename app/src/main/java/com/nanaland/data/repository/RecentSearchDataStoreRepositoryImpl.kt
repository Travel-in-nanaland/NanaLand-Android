package com.nanaland.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import com.nanaland.domain.repository.RecentSearchDataStoreRepository
import com.nanaland.globalvalue.constant.KEY_REFRESH_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

class RecentSearchDataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : RecentSearchDataStoreRepository {

    // 최근 검색어 모두 가져오기
    override suspend fun getAllRecentSearch(): Flow<Map<Preferences.Key<*>, Any>> {
        return dataStore.data.map { it.asMap() }
    }

    // 최근 검색어 추가하기
    override suspend fun addRecentSearch(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit {
            it[dataStoreKey] = value
        }
    }

    // 최근 검색어 지우기
    override suspend fun deleteRecentSearch(key: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit {
            it.remove(dataStoreKey)
        }
    }
}