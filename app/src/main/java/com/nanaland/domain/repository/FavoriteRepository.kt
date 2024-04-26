package com.nanaland.domain.repository

import com.nanaland.domain.request.favorite.GetFavoriteListRequest
import com.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.nanaland.domain.response.favorite.GetFavoriteListResponse
import com.nanaland.domain.response.favorite.ToggleFavoriteResponse
import com.nanaland.util.network.NetworkResult

interface FavoriteRepository {

    // 7대자연 찜리스트 조회
    suspend fun getFavoriteNatureList(
        accessToken: String,
        data: GetFavoriteListRequest
    ): NetworkResult<GetFavoriteListResponse>

    // 전통시장 찜리스트 조회
    suspend fun getFavoriteMarketList(
        accessToken: String,
        data: GetFavoriteListRequest
    ): NetworkResult<GetFavoriteListResponse>

    // 축제 찜리스트 조회
    suspend fun getFavoriteFestivalList(
        accessToken: String,
        data: GetFavoriteListRequest
    ): NetworkResult<GetFavoriteListResponse>

    // 이색체험 찜리스트 조회
    suspend fun getFavoriteExperienceList(
        accessToken: String,
        data: GetFavoriteListRequest
    ): NetworkResult<GetFavoriteListResponse>

    // 전체 찜리스트 조회
    suspend fun getAllFavoriteList(
        accessToken: String,
        data: GetFavoriteListRequest
    ): NetworkResult<GetFavoriteListResponse>

    // 좋아요 토글
    suspend fun toggleFavorite(
        accessToken: String,
        data: ToggleFavoriteRequest
    ): NetworkResult<ToggleFavoriteResponse>
}