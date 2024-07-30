package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.market.MarketContent
import com.jeju.nanaland.domain.response.ResponseWrapper
import com.jeju.nanaland.domain.entity.market.MarketThumbnailListData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarketApi {

    // 전통시장 상세 정보 조회
    @GET("market/{id}")
    suspend fun getMarketContent(
        @Path("id") id: Int,
        @Query("isSearch") isSearch: Boolean
    ): Response<ResponseWrapper<MarketContent>>

    // 전통시장 리스트 조회
    @GET("market/list")
    suspend fun getMarketList(
        @Query("addressFilterList") addressFilterList: List<String>,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<MarketThumbnailListData>>
}