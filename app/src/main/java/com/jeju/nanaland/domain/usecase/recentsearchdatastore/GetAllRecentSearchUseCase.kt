package com.jeju.nanaland.domain.usecase.recentsearchdatastore

import com.jeju.nanaland.domain.repository.RecentSearchDataStoreRepository

class GetAllRecentSearchUseCase(
    private val repository: RecentSearchDataStoreRepository
) {
    operator fun invoke() = repository.getAllRecentSearch()

    fun inContent(category: String) = repository.getAllRecentSearchInContent(category)
}