package com.jeju.nanaland.data.repository

import com.google.gson.GsonBuilder
import com.jeju.nanaland.data.api.MemberApi
import com.jeju.nanaland.domain.entity.member.RecommendedPostData
import com.jeju.nanaland.domain.entity.member.UserProfile
import com.jeju.nanaland.domain.repository.MemberRepository
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.member.UpdateLanguageRequest
import com.jeju.nanaland.domain.request.member.UpdatePolicyAgreementRequest
import com.jeju.nanaland.domain.request.member.UpdateUserProfileRequest
import com.jeju.nanaland.domain.request.member.UpdateUserTypeRequest
import com.jeju.nanaland.domain.request.member.WithdrawalRequest
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

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
        image: UriRequestBody?
    ): NetworkResult<String?> {
        val gson = GsonBuilder().setLenient().setPrettyPrinting().create();
        val json = gson.toJson(data)
        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
        return handleResult { memberApi.updateUserProfile(requestBody, image?.toMultipartBody("multipartFile")) }
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