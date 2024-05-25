package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.MemberApi
import com.jeju.nanaland.domain.repository.MemberRepository
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
import com.jeju.nanaland.util.network.NetworkResultHandler
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class MemberRepositoryImpl(
    private val memberApi: MemberApi
) : MemberRepository, NetworkResultHandler {

    override suspend fun getUserProfile(): NetworkResult<GetUserProfileResponse> {
        return handleResult { memberApi.getUserProfile() }
    }

    // 유저 타입에 따른 추천 게시물 2개 반환
    override suspend fun getRecommendedPost(): NetworkResult<GetRecommendedPostResponse> {
        return handleResult { memberApi.getRecommendedPost() }
    }

    // 테스트 결과에 따른 유저 타입 갱신
    override suspend fun updateUserType(
        data: UpdateUserTypeRequest
    ): NetworkResult<UpdateUserTypeResponse> {
        return handleResult {
            memberApi.updateUserType(
                body = data
            )
        }
    }

    override suspend fun updateUserProfile(
        data: UpdateUserProfileRequest,
        image: File?
    ): NetworkResult<UpdateUserProfileResponse> {
        val multipartImage: MultipartBody.Part? = image?.let {
            val imageBody = image.asRequestBody("image/png".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("images", imageBody.toString(), imageBody)
        }
        return handleResult { memberApi.updateUserProfile(data, multipartImage) }
    }

    override suspend fun withdraw(
        data: WithdrawalRequest
    ): NetworkResult<WithdrawalResponse> {
        return handleResult { memberApi.withdraw(data) }
    }

    override suspend fun signOut(): NetworkResult<SignOutResponse> {
        return handleResult { memberApi.signOut() }
    }

    override suspend fun updateLanguage(
        data: UpdateLanguageRequest
    ): NetworkResult<UpdateLanguageResponse> {
        return handleResult { memberApi.updateLanguage(data) }
    }
}