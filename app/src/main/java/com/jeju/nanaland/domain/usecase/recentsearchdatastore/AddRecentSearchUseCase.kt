package com.jeju.nanaland.domain.usecase.recentsearchdatastore

import com.jeju.nanaland.domain.repository.RecentSearchDataStoreRepository

class AddRecentSearchUseCase(
    private val repository: RecentSearchDataStoreRepository
) {
    suspend operator fun invoke(
        key: String,
        value: String
    ) = repository.addRecentSearch(key, value)
}