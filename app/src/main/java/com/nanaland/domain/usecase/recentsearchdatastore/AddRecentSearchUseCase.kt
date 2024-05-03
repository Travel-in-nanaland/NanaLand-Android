package com.nanaland.domain.usecase.recentsearchdatastore

import com.nanaland.domain.repository.RecentSearchDataStoreRepository

class AddRecentSearchUseCase(
    private val repository: RecentSearchDataStoreRepository
) {
    suspend operator fun invoke(
        key: String,
        value: String
    ) = repository.addRecentSearch(key, value)
}