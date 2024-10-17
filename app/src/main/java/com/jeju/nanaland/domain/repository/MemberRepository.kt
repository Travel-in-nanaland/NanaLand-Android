package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.entity.member.RecommendedPostData
import com.jeju.nanaland.domain.entity.member.UserProfile
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.member.UpdateLanguageRequest
import com.jeju.nanaland.domain.request.member.UpdatePolicyAgreementRequest
import com.jeju.nanaland.domain.request.member.UpdateUserProfileRequest
import com.jeju.nanaland.domain.request.member.UpdateUserTypeRequest
import com.jeju.nanaland.domain.request.member.WithdrawalRequest
import com.jeju.nanaland.util.network.NetworkResult

interface MemberRepository {

    // 유저 프로필 조회
    suspend fun getUserProfile(id: Int? = null): NetworkResult<UserProfile>

    // 유저 타입에 따른 추천 게시물 2개 반환
    suspend fun getRecommendedPost(): NetworkResult<List<RecommendedPostData>>

    // 랜덤 추천 게시물 2개 반환
    suspend fun getRandomRecommendedPost(): NetworkResult<List<RecommendedPostData>>

    // 테스트 결과에 따른 유저 타입 갱신
    suspend fun updateUserType(
        data: UpdateUserTypeRequest
    ): NetworkResult<Any?>

    // 유저 프로필 수정
    suspend fun updateUserProfile(
        data: UpdateUserProfileRequest,
        image: UriRequestBody?
    ): NetworkResult<String?>

    // 회원 탈퇴
    suspend fun withdraw(
        data: WithdrawalRequest
    ): NetworkResult<Any?>

    // 로그아웃
    suspend fun signOut(): NetworkResult<Any?>

    // 언어 설정 변경
    suspend fun updateLanguage(
        data: UpdateLanguageRequest
    ): NetworkResult<Any?>

    // 이용약관 동의 여부 수정
    suspend fun updatePolicyAgreement(
        data: UpdatePolicyAgreementRequest
    ): NetworkResult<Any?>
}