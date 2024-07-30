package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.nature.NatureContent
import com.jeju.nanaland.domain.entity.nature.NatureThumbnailListData
import com.jeju.nanaland.domain.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NatureApi {

    // 7대 자연 상세 정보 조회
    @GET("nature/{id}")
    suspend fun getNatureContent(
        @Path("id") id: Int,
        @Query("isSearch") isSearch: Boolean
    ): Response<ResponseWrapper<NatureContent>>

    // 7대 자연 리스트 조회
    @GET("nature/list")
    suspend fun getNatureList(
        @Query("addressFilterList") addressFilterList: List<String>,
        @Query("keyword") keyword: String?,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<NatureThumbnailListData>>
}