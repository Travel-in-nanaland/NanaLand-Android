package com.nanaland.domain.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface RecentSearchDataStoreRepository {

    // 최근 검색어 모두 가져오기
    suspend fun getAllRecentSearch(): Flow<Map<Preferences.Key<*>, Any>>

    // 최근 검색어 추가하기
    suspend fun addRecentSearch(key: String, value: String)

    // 최근 검색어 지우기
    suspend fun deleteRecentSearch(key: String)
}