package com.jeju.nanaland.domain.usecase.auth

import com.jeju.nanaland.domain.repository.AuthRepository
import com.jeju.nanaland.domain.request.auth.SignInRequest
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