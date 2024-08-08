package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.restaurant.RestaurantContentData
import com.jeju.nanaland.domain.entity.restaurant.RestaurantThumbnailListData
import com.jeju.nanaland.domain.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantApi {

    // 제주 맛집 상세 정보 조회
    @GET("restaurant/{id}")
    suspend fun getRestaurantContent(
        @Path("id") id: Int,
        @Query("isSearch") isSearch: Boolean
    ): Response<ResponseWrapper<RestaurantContentData>>

    // 제주 맛집 리스트 조회
    @GET("restaurant/list")
    suspend fun getRestaurantList(
        @Query("keywordFilterList") keywordFilterList: List<String>,
        @Query("addressFilterList") addressFilterList: List<String>,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<ResponseWrapper<RestaurantThumbnailListData>>
}