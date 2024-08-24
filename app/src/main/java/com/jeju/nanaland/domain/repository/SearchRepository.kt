package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.entity.search.HotPostThumbnailData
import com.jeju.nanaland.domain.entity.search.SearchResultData
import com.jeju.nanaland.domain.request.search.GetAllSearchResultListRequest
import com.jeju.nanaland.domain.request.search.GetSearchResultListRequest
import com.jeju.nanaland.domain.response.search.AllSearchResultListData
import com.jeju.nanaland.util.network.NetworkResult


interface SearchRepository {

    // 검색량 UP 게시물 조회
    suspend fun getHotPosts(): NetworkResult<List<HotPostThumbnailData>>

    // 인기 검색어 8개 조회
    suspend fun getTopKeywords(): NetworkResult<List<String>>

    // 제주맛집 검색 결과
    suspend fun getRestaurantSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<SearchResultData>

    // 자연 검색 결과
    suspend fun getNatureSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<SearchResultData>

    // 나나스픽 검색 결과
    suspend fun getNanaPickSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<SearchResultData>

    // 전통시장 검색 결과
    suspend fun getMarketSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<SearchResultData>

    // 축제 검색 결과
    suspend fun getFestivalSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<SearchResultData>

    // 이색체험 검색 결과
    suspend fun getExperienceSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<SearchResultData>

    // 전체 카테고리 검색 결과 2개씩
    suspend fun getAllSearchResultList(
        data: GetAllSearchResultListRequest
    ): NetworkResult<AllSearchResultListData>
}