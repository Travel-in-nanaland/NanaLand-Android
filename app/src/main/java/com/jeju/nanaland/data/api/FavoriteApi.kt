package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.response.ResponseWrapper
import com.jeju.nanaland.domain.entity.favorite.FavoriteListData
import com.jeju.nanaland.domain.entity.favorite.ToggleFavoriteData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FavoriteApi {

    // 제주맛집 찜리스트 조회
    @GET("favorite/restaurant/list")
    suspend fun getFavoriteRestaurantList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<FavoriteListData>>

    // 7대자연 찜리스트 조회
    @GET("favorite/nature/list")
    suspend fun getFavoriteNatureList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<FavoriteListData>>

    // 나나스픽 찜리스트 조회
    @GET("favorite/nana/list")
    suspend fun getFavoriteNanaPickList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<FavoriteListData>>

    // 전통시장 찜리스트 조회
    @GET("favorite/market/list")
    suspend fun getFavoriteMarketList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<FavoriteListData>>

    // 축제 찜리스트 조회
    @GET("favorite/festival/list")
    suspend fun getFavoriteFestivalList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<FavoriteListData>>

    // 이색체험 찜리스트 조회
    @GET("favorite/experience/list")
    suspend fun getFavoriteExperienceList(
        @Query("page") page: Int,
        @Query("size") size: Int

    ): Response<ResponseWrapper<FavoriteListData>>

    // 전체 찜리스트 조회
    @GET("favorite/all/list")
    suspend fun getAllFavoriteList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<FavoriteListData>>

    // 좋아요 토글
    @POST("favorite/like")
    suspend fun toggleFavorite(
        @Body body: ToggleFavoriteRequest
    ): Response<ResponseWrapper<ToggleFavoriteData>>
}