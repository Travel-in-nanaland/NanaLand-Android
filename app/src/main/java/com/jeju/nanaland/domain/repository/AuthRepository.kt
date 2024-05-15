package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.request.member.SignInRequest
import com.jeju.nanaland.domain.response.member.ReissueAccessTokenResponse
import com.jeju.nanaland.domain.response.member.SignInResponse
import com.jeju.nanaland.util.network.NetworkResult

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