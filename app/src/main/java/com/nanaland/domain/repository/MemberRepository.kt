package com.nanaland.domain.repository

import com.nanaland.domain.request.member.SignInRequest
import com.nanaland.domain.request.member.UpdateUserTypeRequest
import com.nanaland.domain.response.member.GetRecommendedPostResponse
import com.nanaland.domain.response.member.ReissueAccessTokenResponse
import com.nanaland.domain.response.member.SignInResponse
import com.nanaland.domain.response.member.UpdateUserTypeResponse
import com.nanaland.util.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MemberRepository {

    // 유저 타입에 따른 추천 게시물 2개 반환
    suspend fun getRecommendedPost(): NetworkResult<GetRecommendedPostResponse>

    // 테스트 결과에 따른 유저 타입 갱신
    suspend fun updateUserType(
        data: UpdateUserTypeRequest
    ): NetworkResult<UpdateUserTypeResponse>
}