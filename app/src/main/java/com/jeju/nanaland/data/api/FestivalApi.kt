package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.festival.FestivalContentData
import com.jeju.nanaland.domain.response.ResponseWrapper
import com.jeju.nanaland.domain.entity.festival.EndedFestivalListData
import com.jeju.nanaland.domain.entity.festival.MonthlyFestivalListData
import com.jeju.nanaland.domain.entity.festival.SeasonalFestivalListData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FestivalApi {

    // 축제 상세 정보 조회
    @GET("festival/{id}")
    suspend fun getFestivalContent(
        @Path("id") id: Int,
        @Query("isSearch") isSearch: Boolean
    ): Response<ResponseWrapper<FestivalContentData>>

    // 월별 축제 리스트 조회
    @GET("festival/this-month")
    suspend fun getMonthlyFestivalList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("addressFilterList") addressFilterList: List<String>,
        @Query("startDate") startDate: String?,
        @Query("endDate") endDate: String?
    ): Response<ResponseWrapper<MonthlyFestivalListData>>

    // 계절별 축제 리스트 조회
    @GET("festival/season")
    suspend fun getSeasonalFestivalList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("season") season: String
    ): Response<ResponseWrapper<SeasonalFestivalListData>>

    // 종료된 축제 리스트 조회
    @GET("festival/past")
    suspend fun getEndedFestivalList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("addressFilterList") addressFilterList: List<String>,
    ): Response<ResponseWrapper<EndedFestivalListData>>
}