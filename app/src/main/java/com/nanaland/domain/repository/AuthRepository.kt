package com.nanaland.domain.repository

import com.nanaland.domain.request.member.SignInRequest
import com.nanaland.domain.response.member.ReissueAccessTokenResponse
import com.nanaland.domain.response.member.SignInResponse
import com.nanaland.util.network.NetworkResult

interface AuthRepository {

    // AccessToken 재발급
    suspend fun reissueAccessToken(
        refreshToken: String
    ): NetworkResult<ReissueAccessTokenResponse>

    // 로그인
    suspend fun signIn(
        data: SignInRequest
    ): NetworkResult<SignInResponse>
}