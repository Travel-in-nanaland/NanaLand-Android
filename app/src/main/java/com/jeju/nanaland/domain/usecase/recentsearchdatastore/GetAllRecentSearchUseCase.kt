package com.jeju.nanaland.domain.usecase.recentsearchdatastore

import com.jeju.nanaland.domain.repository.RecentSearchDataStoreRepository

class GetAllRecentSearchUseCase(
    private val repository: RecentSearchDataStoreRepository
) {
    suspend operator fun invoke() = repository.getAllRecentSearch()
}