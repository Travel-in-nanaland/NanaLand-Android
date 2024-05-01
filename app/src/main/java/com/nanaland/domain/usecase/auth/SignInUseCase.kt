package com.nanaland.domain.usecase.auth

import com.nanaland.domain.repository.AuthRepository
import com.nanaland.domain.repository.MemberRepository
import com.nanaland.domain.request.member.SignInRequest
import kotlinx.coroutines.flow.flow

class SignInUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(
        data: SignInRequest
    ) = flow {
        val response = repository.signIn(data)
        emit(response)
    }
}