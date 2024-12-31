package com.jeju.nanaland.domain.usecase.auth

import com.jeju.nanaland.domain.repository.AuthRepository
import com.jeju.nanaland.domain.request.auth.SignUpRequest
import kotlinx.coroutines.flow.flow

class SignUpUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(
        data: SignUpRequest,
        image: String?
    ) = flow {
        val response = repository.signUp(data, image)
        emit(response)
    }
}