package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.domain.entity.nanapick.NanaPickContentData
import com.jeju.nanaland.domain.response.ResponseWrapper
import com.jeju.nanaland.domain.entity.nanapick.NanaPickListData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NanaPickApi {

    // 홈화면 미리보기 배너 4개
    @GET("nana")
    suspend fun getHomePreviewBanner(
    ): Response<ResponseWrapper<List<NanaPickBannerData>>>

    // 나나 Pick 리스트
    @GET("nana/list")
    suspend fun getNanaPickList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<NanaPickListData>>

    // 나나 Pick 콘텐츠
    @GET("nana/{contentId}")
    suspend fun getNanaPickContent(
        @Path("contentId") id: Int
    ): Response<ResponseWrapper<NanaPickContentData>>
}