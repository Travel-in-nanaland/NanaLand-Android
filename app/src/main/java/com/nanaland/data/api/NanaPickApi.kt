package com.nanaland.data.api

import com.nanaland.domain.response.nanapick.GetHomePreviewBannerResponse
import com.nanaland.domain.response.nanapick.GetNanaPickContentResponse
import com.nanaland.domain.response.nanapick.GetNanaPickListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NanaPickApi {

    // 홈화면 미리보기 배너 4개
    @GET("nana")
    suspend fun getHomePreviewBanner(
    ): Response<GetHomePreviewBannerResponse>

    // 나나 Pick 리스트
    @GET("nana/list")
    suspend fun getNanaPickList(
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetNanaPickListResponse>

    // 나나 Pick 콘텐츠
    @GET("nana/{contentId}")
    suspend fun getNanaPickContent(
        @Path("contentId") id: Long
    ): Response<GetNanaPickContentResponse>
}