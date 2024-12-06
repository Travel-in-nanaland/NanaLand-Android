package com.jeju.nanaland.domain.usecase.recentsearchdatastore

import com.jeju.nanaland.domain.repository.RecentSearchDataStoreRepository

class ClearRecentSearchDataStoreUseCase(
    private val repository: RecentSearchDataStoreRepository
) {
    suspend operator fun invoke() = repository.clearAll()
    suspend fun inContent(category: String) = repository.clearAllInContent(category)
}