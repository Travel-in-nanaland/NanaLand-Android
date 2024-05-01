package com.nanaland.domain.usecase.search

import com.nanaland.domain.repository.SearchRepository
import com.nanaland.domain.request.search.GetSearchResultListRequest
import kotlinx.coroutines.flow.flow

class GetMarketSearchResultListUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(
        data: GetSearchResultListRequest
    ) = flow {
        val response = repository.getMarketSearchResultList(data)
        emit(response)
    }
}