package com.nanaland.data.repository

import com.nanaland.data.api.AuthApi
import com.nanaland.domain.repository.AuthRepository
import com.nanaland.domain.request.member.SignInRequest
import com.nanaland.domain.response.member.ReissueAccessTokenResponse
import com.nanaland.domain.response.member.SignInResponse
import com.nanaland.util.network.NetworkResult
import com.nanaland.util.network.NetworkResultHandler

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