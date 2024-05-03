package com.nanaland.data.api

import com.nanaland.domain.response.festival.GetEndedFestivalListResponse
import com.nanaland.domain.response.festival.GetFestivalContentResponse
import com.nanaland.domain.response.festival.GetMonthlyFestivalListResponse
import com.nanaland.domain.response.festival.GetSeasonalFestivalListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FestivalApi {

    // 축제 상세 정보 조회
    @GET("festival/{contentId}")
    suspend fun getFestivalContent(
        @Path("contentId") id: Long,
        @Query("isSearch") isSearch: Boolean
    ): Response<GetFestivalContentResponse>

    // 월별 축제 리스트 조회
    @GET("festival/this-month")
    suspend fun getMonthlyFestivalList(
        @Query("page") page: Long,
        @Query("size") size: Long,
        @Query("addressFilterList") addressFilterList: List<String>,
        @Query("startDate") startDate: String?,
        @Query("endDate") endDate: String?
    ): Response<GetMonthlyFestivalListResponse>

    // 계절별 축제 리스트 조회
    @GET("festival/season")
    suspend fun getSeasonalFestivalList(
        @Query("page") page: Long,
        @Query("size") size: Long,
        @Query("season") season: String
    ): Response<GetSeasonalFestivalListResponse>

    // 종료된 축제 리스트 조회
    @GET("festival/past")
    suspend fun getEndedFestivalList(
        @Query("page") page: Long,
        @Query("size") size: Long,
    ): Response<GetEndedFestivalListResponse>
}