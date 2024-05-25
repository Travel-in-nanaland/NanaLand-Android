package com.jeju.nanaland.domain.repository

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
import com.jeju.nanaland.util.network.NetworkResult
import java.io.File

interface MemberRepository {
    // 유저 프로필 조회
    suspend fun getUserProfile(): NetworkResult<GetUserProfileResponse>

    // 유저 타입에 따른 추천 게시물 2개 반환
    suspend fun getRecommendedPost(): NetworkResult<GetRecommendedPostResponse>

    // 테스트 결과에 따른 유저 타입 갱신
    suspend fun updateUserType(
        data: UpdateUserTypeRequest
    ): NetworkResult<UpdateUserTypeResponse>

    // 유저 프로필 수정
    suspend fun updateUserProfile(
        data: UpdateUserProfileRequest,
        image: File?
    ): NetworkResult<UpdateUserProfileResponse>

    // 회원 탈퇴
    suspend fun withdraw(
        data: WithdrawalRequest
    ): NetworkResult<WithdrawalResponse>

    // 로그아웃
    suspend fun signOut(): NetworkResult<SignOutResponse>

    // 언어 설정 변경
    suspend fun updateLanguage(
        data: UpdateLanguageRequest
    ): NetworkResult<UpdateLanguageResponse>
}