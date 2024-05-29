package com.jeju.nanaland.domain.usecase.member

import com.jeju.nanaland.domain.repository.MemberRepository
import kotlinx.coroutines.flow.flow

class GetRandomRecommendedPostUseCase(
    private val repository: MemberRepository
) {
    operator fun invoke() = flow {
        val response = repository.getRandomRecommendedPost()
        emit(response)
    }
}