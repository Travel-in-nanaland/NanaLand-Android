package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.search.HotPostThumbnailData
import com.jeju.nanaland.domain.entity.search.SearchResultData
import com.jeju.nanaland.domain.response.ResponseWrapper
import com.jeju.nanaland.domain.response.search.AllSearchResultListData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    // 검색량 UP 게시물 조회
    @GET("search/volume")
    suspend fun getHotPosts(
    ): Response<ResponseWrapper<List<HotPostThumbnailData>>>

    // 인기 검색어 8개 조회
    @GET("search/popular")
    suspend fun getTopKeywords(
    ): Response<ResponseWrapper<List<String>>>

    // 제주맛집 검색 결과
    @GET("search/restaurant")
    suspend fun getRestaurantSearchResultList(
        @Query("keyword") keyword: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<SearchResultData>>

    // 자연 검색 결과
    @GET("search/nature")
    suspend fun getNatureSearchResultList(
        @Query("keyword") keyword: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<SearchResultData>>

    // 나나스픽 검색 결과
    @GET("search/nana")
    suspend fun getNanaPickSearchResultList(
        @Query("keyword") keyword: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<SearchResultData>>

    // 전통시장 검색 결과
    @GET("search/market")
    suspend fun getMarketSearchResultList(
        @Query("keyword") keyword: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<SearchResultData>>

    // 축제 검색 결과
    @GET("search/festival")
    suspend fun getFestivalSearchResultList(
        @Query("keyword") keyword: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<SearchResultData>>

    // 이색체험 검색 결과
    @GET("search/experience")
    suspend fun getExperienceSearchResultList(
        @Query("keyword") keyword: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponseWrapper<SearchResultData>>

    // 전체 카테고리 검색 결과 2개씩
    @GET("search/all")
    suspend fun getAllSearchResultList(
        @Query("keyword") keyword: String
    ): Response<ResponseWrapper<AllSearchResultListData>>
}