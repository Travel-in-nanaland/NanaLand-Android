package com.nanaland.data.api

import com.nanaland.domain.request.member.UpdateUserTypeRequest
import com.nanaland.domain.response.member.GetRecommendedPostResponse
import com.nanaland.domain.response.member.UpdateUserTypeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface MemberApi {

    // 유저 타입에 따른 추천 게시물 2개 반환
    @GET("member/recommended")
    suspend fun getRecommendedPost(
    ): Response<GetRecommendedPostResponse>

    // 테스트 결과에 따른 유저 타입 갱신
    @PATCH("member/type")
    suspend fun updateUserType(
        @Body body: UpdateUserTypeRequest
    ): Response<UpdateUserTypeResponse>

}