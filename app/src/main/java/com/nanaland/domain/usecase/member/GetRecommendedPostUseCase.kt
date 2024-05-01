package com.nanaland.domain.usecase.member

import com.nanaland.domain.repository.MemberRepository
import kotlinx.coroutines.flow.flow

class GetRecommendedPostUseCase(
    private val repository: MemberRepository
) {
    operator fun invoke() = flow {
        val response = repository.getRecommendedPost()
        emit(response)
    }
}