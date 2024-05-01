package com.nanaland.data.api

import com.nanaland.domain.request.member.SignInRequest
import com.nanaland.domain.response.member.ReissueAccessTokenResponse
import com.nanaland.domain.response.member.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthApi {

    // AccessToken 재발급
    @GET("member/reissue")
    suspend fun reissueAccessToken(
        @Header("Authorization") refreshToken: String
    ): Response<ReissueAccessTokenResponse>

    // 로그인
    @GET("member/login")
    suspend fun signIn(
        @Body body: SignInRequest
    ): Response<SignInResponse>
}