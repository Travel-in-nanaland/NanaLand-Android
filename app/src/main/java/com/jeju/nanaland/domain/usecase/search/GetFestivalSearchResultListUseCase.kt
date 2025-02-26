package com.jeju.nanaland.domain.usecase.search

import com.jeju.nanaland.domain.repository.SearchRepository
import com.jeju.nanaland.domain.request.search.GetSearchResultListRequest
import kotlinx.coroutines.flow.flow

class GetFestivalSearchResultListUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(
        data: GetSearchResultListRequest,
        addressFilterList: List<String>? = null,
        startDate: String? = null,
        endDate: String? = null
    ) = flow {
        val response = repository.getFestivalSearchResultList(data, addressFilterList, startDate, endDate)
        emit(response)
    }
}