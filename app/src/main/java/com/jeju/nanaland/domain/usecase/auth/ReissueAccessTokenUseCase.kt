package com.jeju.nanaland.domain.usecase.auth

import com.jeju.nanaland.domain.repository.AuthRepository
import kotlinx.coroutines.flow.flow

class ReissueAccessTokenUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(
        refreshToken: String
    ) = flow {
        val response = repository.reissueAccessToken(refreshToken)
        emit(response)
    }
}