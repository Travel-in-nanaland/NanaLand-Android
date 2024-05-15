package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.response.search.GetAllSearchResultListResponse
import com.jeju.nanaland.domain.response.search.GetHotPostsResponse
import com.jeju.nanaland.domain.response.search.GetSearchResultListResponse
import com.jeju.nanaland.domain.response.search.GetTopKeywordsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    // 검색량 UP 게시물 조회
    @GET("search/volume")
    suspend fun getHotPosts(
    ): Response<GetHotPostsResponse>

    // 인기 검색어 8개 조회
    @GET("search/popular")
    suspend fun getTopKeywords(
    ): Response<GetTopKeywordsResponse>

    // 자연 검색 결과
    @GET("search/nature")
    suspend fun getNatureSearchResultList(
        @Query("keyword") keyword: String,
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetSearchResultListResponse>

    // 전통시장 검색 결과
    @GET("search/market")
    suspend fun getMarketSearchResultList(
        @Query("keyword") keyword: String,
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetSearchResultListResponse>

    // 축제 검색 결과
    @GET("search/festival")
    suspend fun getFestivalSearchResultList(
        @Query("keyword") keyword: String,
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetSearchResultListResponse>

    // 이색체험 검색 결과
    @GET("search/experience")
    suspend fun getExperienceSearchResultList(
        @Query("keyword") keyword: String,
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetSearchResultListResponse>

    // 전체 카테고리 검색 결과 2개씩
    @GET("search/all")
    suspend fun getAllSearchResultList(
        @Query("keyword") keyword: String
    ): Response<GetAllSearchResultListResponse>
}