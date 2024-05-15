package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.SearchApi
import com.jeju.nanaland.domain.repository.SearchRepository
import com.jeju.nanaland.domain.request.search.GetAllSearchResultListRequest
import com.jeju.nanaland.domain.request.search.GetSearchResultListRequest
import com.jeju.nanaland.domain.response.search.GetAllSearchResultListResponse
import com.jeju.nanaland.domain.response.search.GetHotPostsResponse
import com.jeju.nanaland.domain.response.search.GetSearchResultListResponse
import com.jeju.nanaland.domain.response.search.GetTopKeywordsResponse
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class SearchRepositoryImpl(
    private val searchApi: SearchApi
): SearchRepository, NetworkResultHandler {

    // 검색량 UP 게시물 조회
    override suspend fun getHotPosts(): NetworkResult<GetHotPostsResponse> {
        return handleResult { searchApi.getHotPosts() }
    }

    // 인기 검색어 8개 조회
    override suspend fun getTopKeywords(): NetworkResult<GetTopKeywordsResponse> {
        return handleResult { searchApi.getTopKeywords() }
    }

    // 자연 검색 결과
    override suspend fun getNatureSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<GetSearchResultListResponse> {
        return handleResult {
            searchApi.getNatureSearchResultList(
                keyword = data.keyword,
                page = data.page,
                size = data.size
            )
        }
    }

    // 전통시장 검색 결과
    override suspend fun getMarketSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<GetSearchResultListResponse> {
        return handleResult {
            searchApi.getMarketSearchResultList(
                keyword = data.keyword,
                page = data.page,
                size = data.size
            )
        }
    }

    // 축제 검색 결과
    override suspend fun getFestivalSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<GetSearchResultListResponse> {
        return handleResult {
            searchApi.getFestivalSearchResultList(
                keyword = data.keyword,
                page = data.page,
                size = data.size
            )
        }
    }

    // 이색체험 검색 결과
    override suspend fun getExperienceSearchResultList(
        data: GetSearchResultListRequest
    ): NetworkResult<GetSearchResultListResponse> {
        return handleResult {
            searchApi.getExperienceSearchResultList(
                keyword = data.keyword,
                page = data.page,
                size = data.size
            )
        }
    }

    // 전체 카테고리 검색 결과 2개씩
    override suspend fun getAllSearchResultList(
        data: GetAllSearchResultListRequest
    ): NetworkResult<GetAllSearchResultListResponse> {
        return handleResult {
            searchApi.getAllSearchResultList(
                keyword = data.keyword
            )
        }
    }
}