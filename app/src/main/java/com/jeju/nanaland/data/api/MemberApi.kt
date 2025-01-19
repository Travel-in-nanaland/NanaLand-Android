package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.member.HotPostData
import com.jeju.nanaland.domain.entity.member.RecommendedPostData
import com.jeju.nanaland.domain.entity.member.UserProfile
import com.jeju.nanaland.domain.request.member.UpdateLanguageRequest
import com.jeju.nanaland.domain.request.member.UpdatePolicyAgreementRequest
import com.jeju.nanaland.domain.request.member.UpdateUserProfileRequest
import com.jeju.nanaland.domain.request.member.UpdateUserTypeRequest
import com.jeju.nanaland.domain.request.member.WithdrawalRequest
import com.jeju.nanaland.domain.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface MemberApi {

    // 유저 프로필 조회
    @GET("member/profile")
    suspend fun getUserProfile(
        @Query("id") id: Int? = null
    ): Response<ResponseWrapper<UserProfile>>

    // 유저 타입에 따른 추천 게시물 2개 반환
    @GET("member/recommended")
    suspend fun getRecommendedPost(
        @Query("memberId") memberId: Int? = null
    ): Response<ResponseWrapper<List<RecommendedPostData>>>
    @GET("member/hot")
    suspend fun getHotPost(
    ): Response<ResponseWrapper<List<HotPostData>>>

    // 랜덤 추천 게시물 2개 반환
    @GET("member/recommended/random")
    suspend fun getRandomRecommendedPost(
    ): Response<ResponseWrapper<List<RecommendedPostData>>>

    // 테스트 결과에 따른 유저 타입 갱신
    @PATCH("member/type")
    suspend fun updateUserType(
        @Body body: UpdateUserTypeRequest
    ): Response<ResponseWrapper<Any?>>

    // 유저 프로필 수정
    @PATCH("member/profile")
    suspend fun updateUserProfile(
        @Body data: UpdateUserProfileRequest,
        @Query("fileKey") fileKey: String?
    ): Response<ResponseWrapper<String?>>

    // 회원 탈퇴
    @POST("member/withdrawal")
    suspend fun withdraw(
        @Body body: WithdrawalRequest
    ): Response<ResponseWrapper<Any?>>
    // 회원 탈퇴
    @POST("member/forceWithdrawal")
    suspend fun withdrawForce(
    ): Response<ResponseWrapper<Any?>>

    // 로그아웃
    @POST("member/logout")
    suspend fun signOut(
    ): Response<ResponseWrapper<Any?>>

    // 언어 설정 변경
    @POST("member/language")
    suspend fun updateLanguage(
        @Body body: UpdateLanguageRequest
    ): Response<ResponseWrapper<Any?>>

    // 이용약관 동의 여부 수정
    @POST("member/consent")
    suspend fun updatePolicyAgreement(
        @Body body: UpdatePolicyAgreementRequest
    ): Response<ResponseWrapper<Any?>>
}