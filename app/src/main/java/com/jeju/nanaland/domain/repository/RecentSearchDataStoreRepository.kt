package com.jeju.nanaland.domain.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface RecentSearchDataStoreRepository {

    // 최근 검색어 모두 가져오기
    fun getAllRecentSearch(): Flow<Map<Preferences.Key<*>, Any>>
    fun getAllRecentSearchInContent(category: String): Flow<List<String>>

    // 최근 검색어 추가하기
    suspend fun addRecentSearch(key: String, value: String)
    suspend fun addRecentSearchInContent(category: String, value: String)

    // 최근 검색어 지우기
    suspend fun deleteRecentSearch(key: String)
    suspend fun deleteRecentSearchInContent(category: String, value: String)

    suspend fun clearAll()
    suspend fun clearAllInContent(category: String)
}