package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.response.favorite.GetFavoriteListResponse
import com.jeju.nanaland.domain.response.favorite.ToggleFavoriteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FavoriteApi {

    // 7대자연 찜리스트 조회
    @GET("favorite/nature/list")
    suspend fun getFavoriteNatureList(
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetFavoriteListResponse>

    // 전통시장 찜리스트 조회
    @GET("favorite/market/list")
    suspend fun getFavoriteMarketList(
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetFavoriteListResponse>

    // 축제 찜리스트 조회
    @GET("favorite/festival/list")
    suspend fun getFavoriteFestivalList(
        @Query("page") page: Long,
        @Query("size") size: Long

    ): Response<GetFavoriteListResponse>

    // 이색체험 찜리스트 조회
    @GET("favorite/experience/list")
    suspend fun getFavoriteExperienceList(
        @Query("page") page: Long,
        @Query("size") size: Long

    ): Response<GetFavoriteListResponse>

    // 전체 찜리스트 조회
    @GET("favorite/all/list")
    suspend fun getAllFavoriteList(
        @Query("page") page: Long,
        @Query("size") size: Long

    ): Response<GetFavoriteListResponse>

    // 좋아요 토글
    @POST("favorite/like")
    suspend fun toggleFavorite(
        @Body body: ToggleFavoriteRequest
    ): Response<ToggleFavoriteResponse>
}