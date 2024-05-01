package com.nanaland.domain.usecase.search

import com.nanaland.domain.repository.SearchRepository
import kotlinx.coroutines.flow.flow

// 인기 검색어 8개 조회
class GetTopKeywordsUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke() = flow {
        val response = repository.getTopKeywords()
        emit(response)
    }
}