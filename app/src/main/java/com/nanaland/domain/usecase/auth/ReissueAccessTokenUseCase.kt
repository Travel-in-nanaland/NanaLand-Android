package com.nanaland.domain.usecase.auth

import com.nanaland.domain.repository.AuthRepository
import com.nanaland.domain.repository.MemberRepository
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