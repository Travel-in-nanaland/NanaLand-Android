package com.jeju.nanaland.domain.usecase.search

import com.jeju.nanaland.domain.repository.SearchRepository
import com.jeju.nanaland.domain.request.search.GetSearchResultListRequest
import kotlinx.coroutines.flow.flow

// 자연 검색 결과
class GetNatureSearchResultListUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(
        data: GetSearchResultListRequest
    ) = flow {
        val response = repository.getNatureSearchResultList(data)
        emit(response)
    }
}