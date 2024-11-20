package com.jeju.nanaland.domain.usecase.member

import com.jeju.nanaland.domain.repository.MemberRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHotPostUseCase @Inject constructor(
    private val repository: MemberRepository
) {
    operator fun invoke() = flow {
        val response = repository.getHotPost()
        emit(response)
    }
}