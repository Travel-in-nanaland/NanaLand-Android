package com.nanaland.domain.usecase.search

import com.nanaland.domain.repository.SearchRepository
import kotlinx.coroutines.flow.flow

class GetHotPostsUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke() = flow {
        val response = repository.getHotPosts()
        emit(response)
    }
}