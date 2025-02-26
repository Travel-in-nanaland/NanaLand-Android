package com.jeju.nanaland.domain.usecase.search

import com.jeju.nanaland.domain.repository.SearchRepository
import com.jeju.nanaland.domain.request.search.GetSearchResultListRequest
import kotlinx.coroutines.flow.flow

class GetRestaurantSearchResultListUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(
        data: GetSearchResultListRequest,
        addressFilterList: List<String>? = null,
        keywordFilterList: List<String>? = null
    ) = flow {
        val response = repository.getRestaurantSearchResultList(data, addressFilterList, keywordFilterList)
        emit(response)
    }
}