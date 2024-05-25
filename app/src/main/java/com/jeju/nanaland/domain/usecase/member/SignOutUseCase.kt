package com.jeju.nanaland.domain.usecase.member

import com.jeju.nanaland.domain.repository.MemberRepository
import kotlinx.coroutines.flow.flow

class SignOutUseCase(
    private val repository: MemberRepository
) {
    operator fun invoke() = flow {
        val response = repository.signOut()
        emit(response)
    }
}