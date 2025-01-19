package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.entity.favorite.ToggleFavoriteData
import com.jeju.nanaland.domain.request.favorite.GetFavoriteListRequest
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.entity.favorite.FavoriteListData
import com.jeju.nanaland.util.network.NetworkResult

interface FavoriteRepository {

    // 제주맛집 찜리스트 조회
    suspend fun getFavoriteRestaurantList(
        data: GetFavoriteListRequest
    ): NetworkResult<FavoriteListData>

    // 7대자연 찜리스트 조회
    suspend fun getFavoriteNatureList(
        data: GetFavoriteListRequest
    ): NetworkResult<FavoriteListData>

    // 나나스픽 찜리스트 조회
    suspend fun getFavoriteNanaPickList(
        data: GetFavoriteListRequest
    ): NetworkResult<FavoriteListData>

    // 전통시장 찜리스트 조회
    suspend fun getFavoriteMarketList(
        data: GetFavoriteListRequest
    ): NetworkResult<FavoriteListData>

    // 축제 찜리스트 조회
    suspend fun getFavoriteFestivalList(
        data: GetFavoriteListRequest
    ): NetworkResult<FavoriteListData>

    // 이색체험 찜리스트 조회
    suspend fun getFavoriteExperienceList(
        data: GetFavoriteListRequest,
        type: String
    ): NetworkResult<FavoriteListData>

    // 전체 찜리스트 조회
    suspend fun getAllFavoriteList(
        data: GetFavoriteListRequest
    ): NetworkResult<FavoriteListData>

    // 좋아요 토글
    suspend fun toggleFavorite(
        data: ToggleFavoriteRequest
    ): NetworkResult<ToggleFavoriteData>
}