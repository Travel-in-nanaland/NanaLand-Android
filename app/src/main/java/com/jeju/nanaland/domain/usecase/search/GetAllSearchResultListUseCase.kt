package com.jeju.nanaland.domain.usecase.search

import com.jeju.nanaland.domain.repository.SearchRepository
import com.jeju.nanaland.domain.request.search.GetAllSearchResultListRequest
import kotlinx.coroutines.flow.flow

class GetAllSearchResultListUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(
        data: GetAllSearchResultListRequest
    ) = flow {
        val response = repository.getAllSearchResultList(data)
        emit(response)
    }
}