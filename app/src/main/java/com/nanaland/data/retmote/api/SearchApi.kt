package com.nanaland.data.retmote.api

import com.nanaland.domain.response.search.GetTopKeywordsResponse
import com.nanaland.domain.response.search.GetAllSearchResultListResponse
import com.nanaland.domain.response.search.GetSearchResultListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchApi {

    // 인기 검색어 8개 조회
    @GET("search/popular")
    suspend fun getTopKeywords(
        @Header("Authorization") accessToken: String
    ): Response<GetTopKeywordsResponse>

    // 자연 검색 결과
    @GET("search/nature")
    suspend fun getNatureSearchResultList(
        @Header("Authorization") accessToken: String,
        @Query("keyword") keyword: String,
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetSearchResultListResponse>

    // 전통시장 검색 결과
    @GET("search/market")
    suspend fun getMarketSearchResultList(
        @Header("Authorization") accessToken: String,
        @Query("keyword") keyword: String,
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetSearchResultListResponse>

    // 축제 검색 결과
    @GET("search/festival")
    suspend fun getFestivalSearchResultList(
        @Header("Authorization") accessToken: String,
        @Query("keyword") keyword: String,
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetSearchResultListResponse>

    // 이색체험 검색 결과
    @GET("search/experience")
    suspend fun getExperienceSearchResultList(
        @Header("Authorization") accessToken: String,
        @Query("keyword") keyword: String,
        @Query("page") page: Long,
        @Query("size") size: Long
    ): Response<GetSearchResultListResponse>

    // 전체 카테고리 검색 결과 2개씩
    @GET("search/category")
    suspend fun getAllSearchResultList(
        @Header("Authorization") accessToken: String,
        @Query("keyword") keyword: String
    ): Response<GetAllSearchResultListResponse>
}