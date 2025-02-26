package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.SearchApi
import com.jeju.nanaland.domain.entity.search.HotPostThumbnailData
import com.jeju.nanaland.domain.entity.search.SearchResultData
import com.jeju.nanaland.domain.repository.SearchRepository
import com.jeju.nanaland.domain.request.search.GetAllSearchResultListRequest
import com.jeju.nanaland.domain.request.search.GetSearchResultListRequest
import com.jeju.nanaland.domain.response.search.AllSearchResultListData
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class SearchRepositoryImpl(
    private val searchApi: SearchApi
): SearchRepository, NetworkResultHandler {

    // 검색량 UP 게시물 조회
    override suspend fun getHotPosts(): NetworkResult<List<HotPostThumbnailData>> {
        return handleResult { searchApi.getHotPosts() }
    }

    // 인기 검색어 8개 조회
    override suspend fun getTopKeywords(): NetworkResult<List<String>> {
        return handleResult { searchApi.getTopKeywords() }
    }

    override suspend fun getRestaurantSearchResultList(
        data: GetSearchResultListRequest,
        addressFilterList: List<String>?,
        keywordFilterList: List<String>?
    ): NetworkResult<SearchResultData> {
        return handleResult {
            searchApi.getRestaurantSearchResultList(
                keyword = data.keyword,
                page = data.page,
                size = data.size,
                addressFilterList = addressFilterList,
                keywordFilterList = keywordFilterList
            )
        }
    }

    // 자연 검색 결과
    override suspend fun getNatureSearchResultList(
        data: GetSearchResultListRequest,
        addressFilterList: List<String>?
    ): NetworkResult<SearchResultData> {
        return handleResult {
            searchApi.getNatureSearchResultList(
                keyword = data.keyword,
                page = data.page,
                size = data.size,
                addressFilterList = addressFilterList
            )
        }
    }

    // 나나스픽 검색 결과
    override suspend fun getNanaPickSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<SearchResultData> {
        return handleResult {
            searchApi.getNanaPickSearchResultList(
                keyword = data.keyword,
                page = data.page,
                size = data.size
            )
        }
    }

    // 전통시장 검색 결과
    override suspend fun getMarketSearchResultList(
        data: GetSearchResultListRequest,
        addressFilterList: List<String>?
    ): NetworkResult<SearchResultData> {
        return handleResult {
            searchApi.getMarketSearchResultList(
                keyword = data.keyword,
                page = data.page,
                size = data.size,
                addressFilterList = addressFilterList,
            )
        }
    }

    // 축제 검색 결과
    override suspend fun getFestivalSearchResultList(
        data: GetSearchResultListRequest,
        addressFilterList: List<String>?,
        startDate: String?,
        endDate: String?
    ): NetworkResult<SearchResultData> {
        return handleResult {
            searchApi.getFestivalSearchResultList(
                keyword = data.keyword,
                page = data.page,
                size = data.size,
                addressFilterList = addressFilterList,
                startDate = startDate,
                endDate = endDate
            )
        }
    }

    // 이색체험 검색 결과
    override suspend fun getExperienceSearchResultList(
        data: GetSearchResultListRequest,
        type: String,
        addressFilterList: List<String>?,
        keywordFilterList: List<String>?
    ): NetworkResult<SearchResultData> {
        return handleResult {
            searchApi.getExperienceSearchResultList(
                keyword = data.keyword,
                type = type,
                page = data.page,
                size = data.size,
                addressFilterList = addressFilterList,
                keywordFilterList = keywordFilterList
            )
        }
    }

    // 전체 카테고리 검색 결과 2개씩
    override suspend fun getAllSearchResultList(
        data: GetAllSearchResultListRequest
    ): NetworkResult<AllSearchResultListData> {
        return handleResult {
            searchApi.getAllSearchResultList(
                keyword = data.keyword
            )
        }
    }
}