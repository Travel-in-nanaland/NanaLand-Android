package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.auth.AuthTokenData
import com.jeju.nanaland.domain.request.auth.SignInRequest
import com.jeju.nanaland.domain.response.ResponseWrapper
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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
    @Multipart
    @POST("member/join")
    suspend fun signUp(
        @Part("reqDto") data: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<ResponseWrapper<AuthTokenData>>
}