package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.AuthApi
import com.jeju.nanaland.domain.repository.AuthRepository
import com.jeju.nanaland.domain.request.member.SignInRequest
import com.jeju.nanaland.domain.response.member.ReissueAccessTokenResponse
import com.jeju.nanaland.domain.response.member.SignInResponse
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class AuthRepositoryImpl(
    private val authApi: AuthApi
) : AuthRepository, NetworkResultHandler {

    // AccessToken 재발급
    override suspend fun reissueAccessToken(
        refreshToken: String
    ): NetworkResult<ReissueAccessTokenResponse> {
        return handleResult { authApi.reissueAccessToken("Bearer $refreshToken") }
    }

    // 로그인
    override suspend fun signIn(
        data: SignInRequest
    ): NetworkResult<SignInResponse> {
        return handleResult { authApi.signIn(body = data) }
    }
}