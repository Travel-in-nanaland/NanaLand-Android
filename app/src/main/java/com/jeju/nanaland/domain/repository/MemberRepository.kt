package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.request.member.UpdateUserTypeRequest
import com.jeju.nanaland.domain.response.member.GetRecommendedPostResponse
import com.jeju.nanaland.domain.response.member.UpdateUserTypeResponse
import com.jeju.nanaland.util.network.NetworkResult

interface MemberRepository {

    // 유저 타입에 따른 추천 게시물 2개 반환
    suspend fun getRecommendedPost(): NetworkResult<GetRecommendedPostResponse>

    // 테스트 결과에 따른 유저 타입 갱신
    suspend fun updateUserType(
        data: UpdateUserTypeRequest
    ): NetworkResult<UpdateUserTypeResponse>
}