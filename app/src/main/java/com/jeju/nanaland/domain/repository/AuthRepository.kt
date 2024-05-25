package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.request.auth.SignInRequest
import com.jeju.nanaland.domain.request.auth.SignUpRequest
import com.jeju.nanaland.domain.response.auth.ReissueAccessTokenResponse
import com.jeju.nanaland.domain.response.auth.SignInResponse
import com.jeju.nanaland.domain.response.auth.SignUpResponse
import com.jeju.nanaland.util.network.NetworkResult
import java.io.File

interface AuthRepository {

    // AccessToken 재발급
    suspend fun reissueAccessToken(
        refreshToken: String
    ): NetworkResult<ReissueAccessTokenResponse>

    // 로그인
    suspend fun signIn(
        data: SignInRequest
    ): NetworkResult<SignInResponse>

    // 회원가입
    suspend fun signUp(
        data: SignUpRequest,
        image: File?
    ): NetworkResult<SignUpResponse>
}