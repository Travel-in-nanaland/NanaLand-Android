package com.jeju.nanaland.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jeju.nanaland.domain.repository.RecentSearchDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecentSearchDataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val dataStoreInContent: DataStore<Preferences>
) : RecentSearchDataStoreRepository {

    // 최근 검색어 모두 가져오기
    override fun getAllRecentSearch(): Flow<Map<Preferences.Key<*>, Any>> {
        return dataStore.data.map { it.asMap() }
    }
    override fun getAllRecentSearchInContent(category: String): Flow<List<String>> {
        return dataStoreInContent.data.map {
            it[stringPreferencesKey(category)]
                ?.split('\n')
                ?.reversed()
                ?: emptyList()
        }
    }

    // 최근 검색어 추가하기
    override suspend fun addRecentSearch(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit {
            it[dataStoreKey] = value
        }
    }
    override suspend fun addRecentSearchInContent(category: String, value: String) {
        val key = stringPreferencesKey(category)
        dataStoreInContent.edit {
            if(value.isBlank()) return@edit

            val inputValue:MutableList<String> = it[key]?.split('\n')?.toMutableList() ?: mutableListOf()
            inputValue.remove(value)
            inputValue.add(value)

            it[key] = inputValue.joinToString("\n")
        }
    }

    // 최근 검색어 지우기
    override suspend fun deleteRecentSearch(key: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit {
            it.remove(dataStoreKey)
        }
    }
    // 최근 검색어 지우기
    override suspend fun deleteRecentSearchInContent(category: String, value: String) {
        val key = stringPreferencesKey(category)
        dataStoreInContent.edit {
            val inputValue:MutableList<String> = it[key]?.split('\n')?.toMutableList() ?: mutableListOf()
            inputValue.remove(value)

            it[key] = inputValue.joinToString("\n")
        }
    }

    override suspend fun clearAll() {
        dataStore.edit {
            it.clear()
        }
    }
    override suspend fun clearAllInContent(category: String) {
        val key = stringPreferencesKey(category)
        dataStoreInContent.edit {
            it.remove(key)
        }
    }
}