package com.nanaland.data.retmote.api

import com.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.nanaland.domain.response.favorite.GetFavoriteListResponse
import com.nanaland.domain.response.favorite.ToggleFavoriteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FavoriteApi {

    // 7대자연 찜리스트 조회
    @GET("favorite/nature/list")
    suspend fun getFavoriteNatureList(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetFavoriteListResponse>

    // 전통시장 찜리스트 조회
    @GET("favorite/market/list")
    suspend fun getFavoriteMarketList(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetFavoriteListResponse>

    // 축제 찜리스트 조회
    @GET("favorite/festival/list")
    suspend fun getFavoriteFestivalList(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Long,
        @Query("size") size: Long

    ): Response<GetFavoriteListResponse>

    // 이색체험 찜리스트 조회
    @GET("favorite/experience/list")
    suspend fun getFavoriteExperienceList(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Long,
        @Query("size") size: Long

    ): Response<GetFavoriteListResponse>

    // 전체 찜리스트 조회
    @GET("favorite/all/list")
    suspend fun getAllFavoriteList(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Long,
        @Query("size") size: Long

    ): Response<GetFavoriteListResponse>

    // 좋아요 토글
    @GET("favorite/like/{contentId}")
    suspend fun toggleFavorite(
        @Header("Authorization") accessToken: String,
        @Body body: ToggleFavoriteRequest
    ): Response<ToggleFavoriteResponse>
}