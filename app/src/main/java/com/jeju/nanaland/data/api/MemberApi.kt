package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.request.member.UpdateLanguageRequest
import com.jeju.nanaland.domain.request.member.UpdateUserProfileRequest
import com.jeju.nanaland.domain.request.member.UpdateUserTypeRequest
import com.jeju.nanaland.domain.request.member.WithdrawalRequest
import com.jeju.nanaland.domain.response.member.GetRecommendedPostResponse
import com.jeju.nanaland.domain.response.member.GetUserProfileResponse
import com.jeju.nanaland.domain.response.member.SignOutResponse
import com.jeju.nanaland.domain.response.member.UpdateLanguageResponse
import com.jeju.nanaland.domain.response.member.UpdateUserProfileResponse
import com.jeju.nanaland.domain.response.member.UpdateUserTypeResponse
import com.jeju.nanaland.domain.response.member.WithdrawalResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part

interface MemberApi {

    // 유저 프로필 조회
    @GET("member/profile")
    suspend fun getUserProfile(
    ): Response<GetUserProfileResponse>

    // 유저 타입에 따른 추천 게시물 2개 반환
    @GET("member/recommended")
    suspend fun getRecommendedPost(
    ): Response<GetRecommendedPostResponse>

    // 테스트 결과에 따른 유저 타입 갱신
    @PATCH("member/type")
    suspend fun updateUserType(
        @Body body: UpdateUserTypeRequest
    ): Response<UpdateUserTypeResponse>

    // 유저 프로필 수정
    @PATCH("member/profile")
    suspend fun updateUserProfile(
        @Body body: UpdateUserProfileRequest,
        @Part image: MultipartBody.Part?
    ): Response<UpdateUserProfileResponse>

    // 회원 탈퇴
    @POST("member/withdrawal")
    suspend fun withdraw(
        @Body body: WithdrawalRequest
    ): Response<WithdrawalResponse>

    // 로그아웃
    @POST("member/logout")
    suspend fun signOut(
    ): Response<SignOutResponse>

    // 언어 설정 변경
    @POST("member/language")
    suspend fun updateLanguage(
        @Body body: UpdateLanguageRequest
    ): Response<UpdateLanguageResponse>
}