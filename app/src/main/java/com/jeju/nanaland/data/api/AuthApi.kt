package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.auth.AuthTokenData
import com.jeju.nanaland.domain.request.auth.SignInRequest
import com.jeju.nanaland.domain.request.auth.SignUpRequest
import com.jeju.nanaland.domain.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    // AccessToken 재발급
    @GET("member/reissue")
    suspend fun reissueAccessToken(
        @Header("Authorization") refreshToken: String,
        @Query("fcmToken") fcmToken: String?,
    ): Response<ResponseWrapper<AuthTokenData>>

    // 로그인
    @POST("member/login")
    suspend fun signIn(
        @Body body: SignInRequest
    ): Response<ResponseWrapper<AuthTokenData>>

    // 회원가입
    @POST("member/join")
    suspend fun signUp(
        @Body data: SignUpRequest,
        @Query("fileKey") image: String?
    ): Response<ResponseWrapper<AuthTokenData>>

    // 닉네임 중복 확인
    @GET("member/validateNickname")
    suspend fun duplicateNickname(
        @Query("nickname") data: String,
    ): Response<ResponseWrapper<Unit>>
}