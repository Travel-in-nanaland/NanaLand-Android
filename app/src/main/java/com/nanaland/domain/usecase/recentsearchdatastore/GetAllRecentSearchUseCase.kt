package com.nanaland.domain.usecase.recentsearchdatastore

import com.nanaland.domain.repository.RecentSearchDataStoreRepository

class GetAllRecentSearchUseCase(
    private val repository: RecentSearchDataStoreRepository
) {
    suspend operator fun invoke() = repository.getAllRecentSearch()
}