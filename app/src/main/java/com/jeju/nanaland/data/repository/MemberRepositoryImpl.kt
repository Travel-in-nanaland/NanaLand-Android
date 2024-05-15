package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.MemberApi
import com.jeju.nanaland.domain.repository.MemberRepository
import com.jeju.nanaland.domain.request.member.UpdateUserTypeRequest
import com.jeju.nanaland.domain.response.member.GetRecommendedPostResponse
import com.jeju.nanaland.domain.response.member.UpdateUserTypeResponse
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class MemberRepositoryImpl(
    private val memberApi: MemberApi
) : MemberRepository, NetworkResultHandler {

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
}