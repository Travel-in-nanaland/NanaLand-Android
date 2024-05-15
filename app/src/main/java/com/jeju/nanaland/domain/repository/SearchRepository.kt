package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.request.search.GetAllSearchResultListRequest
import com.jeju.nanaland.domain.request.search.GetSearchResultListRequest
import com.jeju.nanaland.domain.response.search.GetAllSearchResultListResponse
import com.jeju.nanaland.domain.response.search.GetHotPostsResponse
import com.jeju.nanaland.domain.response.search.GetSearchResultListResponse
import com.jeju.nanaland.domain.response.search.GetTopKeywordsResponse
import com.jeju.nanaland.util.network.NetworkResult


interface SearchRepository {

    // 검색량 UP 게시물 조회
    suspend fun getHotPosts(): NetworkResult<GetHotPostsResponse>

    // 인기 검색어 8개 조회
    suspend fun getTopKeywords(): NetworkResult<GetTopKeywordsResponse>

    // 자연 검색 결과
    suspend fun getNatureSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<GetSearchResultListResponse>

    // 전통시장 검색 결과
    suspend fun getMarketSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<GetSearchResultListResponse>

    // 축제 검색 결과
    suspend fun getFestivalSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<GetSearchResultListResponse>

    // 이색체험 검색 결과
    suspend fun getExperienceSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<GetSearchResultListResponse>

    // 전체 카테고리 검색 결과 2개씩
    suspend fun getAllSearchResultList(
        data: GetAllSearchResultListRequest
    ): NetworkResult<GetAllSearchResultListResponse>
}