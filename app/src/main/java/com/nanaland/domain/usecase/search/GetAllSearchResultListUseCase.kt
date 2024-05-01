package com.nanaland.domain.usecase.search

import com.nanaland.domain.repository.SearchRepository
import com.nanaland.domain.request.search.GetAllSearchResultListRequest
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