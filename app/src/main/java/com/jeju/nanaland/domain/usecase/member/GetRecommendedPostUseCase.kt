package com.jeju.nanaland.domain.usecase.member

import com.jeju.nanaland.domain.repository.MemberRepository
import kotlinx.coroutines.flow.flow

class GetRecommendedPostUseCase(
    private val repository: MemberRepository
) {
    operator fun invoke() = flow {
        val response = repository.getRecommendedPost()
        emit(response)
    }
}