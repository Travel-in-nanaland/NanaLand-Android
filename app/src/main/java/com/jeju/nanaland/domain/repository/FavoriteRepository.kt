package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.request.favorite.GetFavoriteListRequest
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.response.favorite.GetFavoriteListResponse
import com.jeju.nanaland.domain.response.favorite.ToggleFavoriteResponse
import com.jeju.nanaland.util.network.NetworkResult

interface FavoriteRepository {

    // 7대자연 찜리스트 조회
    suspend fun getFavoriteNatureList(
        data: GetFavoriteListRequest
    ): NetworkResult<GetFavoriteListResponse>

    // 전통시장 찜리스트 조회
    suspend fun getFavoriteMarketList(
        data: GetFavoriteListRequest
    ): NetworkResult<GetFavoriteListResponse>

    // 축제 찜리스트 조회
    suspend fun getFavoriteFestivalList(
        data: GetFavoriteListRequest
    ): NetworkResult<GetFavoriteListResponse>

    // 이색체험 찜리스트 조회
    suspend fun getFavoriteExperienceList(
        data: GetFavoriteListRequest
    ): NetworkResult<GetFavoriteListResponse>

    // 전체 찜리스트 조회
    suspend fun getAllFavoriteList(
        data: GetFavoriteListRequest
    ): NetworkResult<GetFavoriteListResponse>

    // 좋아요 토글
    suspend fun toggleFavorite(
        data: ToggleFavoriteRequest
    ): NetworkResult<ToggleFavoriteResponse>
}