package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.MemberApi
import com.jeju.nanaland.domain.entity.member.HotPostData
import com.jeju.nanaland.domain.entity.member.RecommendedPostData
import com.jeju.nanaland.domain.entity.member.UserProfile
import com.jeju.nanaland.domain.repository.MemberRepository
import com.jeju.nanaland.domain.request.member.UpdateLanguageRequest
import com.jeju.nanaland.domain.request.member.UpdatePolicyAgreementRequest
import com.jeju.nanaland.domain.request.member.UpdateUserProfileRequest
import com.jeju.nanaland.domain.request.member.UpdateUserTypeRequest
import com.jeju.nanaland.domain.request.member.WithdrawalRequest
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class MemberRepositoryImpl(
    private val memberApi: MemberApi
) : MemberRepository, NetworkResultHandler {

    override suspend fun getUserProfile(id: Int?): NetworkResult<UserProfile> {
        return handleResult { memberApi.getUserProfile(id) }
    }

    // 유저 타입에 따른 추천 게시물 2개 반환
    override suspend fun getRecommendedPost(): NetworkResult<List<RecommendedPostData>> {
        return handleResult { memberApi.getRecommendedPost() }
    }
    override suspend fun getHotPost(): NetworkResult<List<HotPostData>> {
        return handleResult { memberApi.getHotPost() }
    }

    override suspend fun getRandomRecommendedPost(): NetworkResult<List<RecommendedPostData>> {
        return handleResult { memberApi.getRandomRecommendedPost() }
    }

    // 테스트 결과에 따른 유저 타입 갱신
    override suspend fun updateUserType(
        data: UpdateUserTypeRequest
    ): NetworkResult<Any?> {
        return handleResult {
            memberApi.updateUserType(
                body = data
            )
        }
    }

    override suspend fun updateUserProfile(
        data: UpdateUserProfileRequest,
        image: String?
    ): NetworkResult<String?> {
        return handleResult { memberApi.updateUserProfile(data, image) }
    }

    override suspend fun withdraw(
        data: WithdrawalRequest
    ): NetworkResult<Any?> {
        return handleResult { memberApi.withdraw(data) }
    }

    override suspend fun signOut(): NetworkResult<Any?> {
        return handleResult { memberApi.signOut() }
    }

    override suspend fun updateLanguage(
        data: UpdateLanguageRequest
    ): NetworkResult<Any?> {
        return handleResult { memberApi.updateLanguage(data) }
    }

    override suspend fun updatePolicyAgreement(data: UpdatePolicyAgreementRequest): NetworkResult<Any?> {
        return handleResult { memberApi.updatePolicyAgreement(data) }
    }
}