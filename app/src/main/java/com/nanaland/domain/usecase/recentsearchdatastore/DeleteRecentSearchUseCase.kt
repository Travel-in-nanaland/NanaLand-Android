package com.nanaland.domain.usecase.recentsearchdatastore

import com.nanaland.domain.repository.RecentSearchDataStoreRepository

class DeleteRecentSearchUseCase(
    private val repository: RecentSearchDataStoreRepository
) {
    suspend operator fun invoke(key: String) = repository.deleteRecentSearch(key)
}