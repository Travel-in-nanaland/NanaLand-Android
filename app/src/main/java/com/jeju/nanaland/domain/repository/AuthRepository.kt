package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.entity.auth.AuthTokenData
import com.jeju.nanaland.domain.request.auth.SignInRequest
import com.jeju.nanaland.domain.request.auth.SignUpRequest
import com.jeju.nanaland.util.network.NetworkResult
import java.io.File

interface AuthRepository {

    // AccessToken 재발급
    suspend fun reissueAccessToken(
        refreshToken: String
    ): NetworkResult<AuthTokenData>

    // 로그인
    suspend fun signIn(
        data: SignInRequest
    ): NetworkResult<AuthTokenData>

    // 회원가입
    suspend fun signUp(
        data: SignUpRequest,
        image: File?
    ): NetworkResult<AuthTokenData>

    // FCM 토큰 발행
    suspend fun getFCMToken(
    ): String?

    // 닉네임 중복 확인
    suspend fun duplicateNickname(
        data: String,
    ): NetworkResult<Unit>
}