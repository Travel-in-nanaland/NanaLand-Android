package com.jeju.nanaland.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserSettingsDataStoreRepository {

    fun getValue(key: String): Flow<String?>

    suspend fun saveValue(key: String, value: String)

    suspend fun clearAll()
}