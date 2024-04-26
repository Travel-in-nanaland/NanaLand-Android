package com.nanaland.data.retmote.api

import com.nanaland.domain.response.market.GetMarketContentResponse
import com.nanaland.domain.response.market.GetMarketListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MarketApi {

    // 전통시장 상세 정보 조회
    @GET("market/{contentId}")
    suspend fun getMarketContent(
        @Header("Authorization") accessToken: String,
        @Path("contentId") id: Long
    ): Response<GetMarketContentResponse>

    // 전통시장 리스트 조회
    @GET("market/list")
    suspend fun getMarketList(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetMarketListResponse>
}