package com.jeju.nanaland.domain.usecase.search

import com.jeju.nanaland.domain.repository.SearchRepository
import com.jeju.nanaland.domain.request.search.GetSearchResultListRequest
import kotlinx.coroutines.flow.flow

// 자연 검색 결과
class GetNatureSearchResultListUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(
        data: GetSearchResultListRequest,
        addressFilterList: List<String>? = null
    ) = flow {
        val response = repository.getNatureSearchResultList(data, addressFilterList)
        emit(response)
    }
}