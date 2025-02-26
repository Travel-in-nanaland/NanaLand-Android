package com.jeju.nanaland.domain.usecase.search

import com.jeju.nanaland.domain.repository.SearchRepository
import com.jeju.nanaland.domain.request.search.GetSearchResultListRequest
import kotlinx.coroutines.flow.flow

class GetMarketSearchResultListUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(
        data: GetSearchResultListRequest,
        addressFilterList: List<String>? = null
    ) = flow {
        val response = repository.getMarketSearchResultList(data, addressFilterList)
        emit(response)
    }
}