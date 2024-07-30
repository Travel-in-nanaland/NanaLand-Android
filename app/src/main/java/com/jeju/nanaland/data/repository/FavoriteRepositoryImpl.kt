package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.FavoriteApi
import com.jeju.nanaland.domain.entity.favorite.ToggleFavoriteData
import com.jeju.nanaland.domain.repository.FavoriteRepository
import com.jeju.nanaland.domain.request.favorite.GetFavoriteListRequest
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.entity.favorite.FavoriteListData
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class FavoriteRepositoryImpl(
    private val favoriteApi: FavoriteApi
) : FavoriteRepository, NetworkResultHandler {

    // 7대자연 찜리스토 조회
    override suspend fun getFavoriteNatureList(
        data: GetFavoriteListRequest
    ): NetworkResult<FavoriteListData> {
        return handleResult {
            favoriteApi.getFavoriteNatureList(
                page = data.page,
                size = data.size
            )
        }
    }

    // 전통시장 찜리스트 조회
    override suspend fun getFavoriteMarketList(
        data: GetFavoriteListRequest
    ): NetworkResult<FavoriteListData> {
        return handleResult {
            favoriteApi.getFavoriteMarketList(
                page = data.page,
                size = data.size
            )
        }
    }

    // 축제 찜리스트 조회
    override suspend fun getFavoriteFestivalList(
        data: GetFavoriteListRequest
    ): NetworkResult<FavoriteListData> {
        return handleResult {
            favoriteApi.getFavoriteFestivalList(
                page = data.page,
                size = data.size
            )
        }
    }

    // 이색체험 찜리스트 조회
    override suspend fun getFavoriteExperienceList(
        data: GetFavoriteListRequest
    ): NetworkResult<FavoriteListData> {
        return handleResult {
            favoriteApi.getFavoriteExperienceList(
                page = data.page,
                size = data.size
            )
        }
    }

    // 전체 찜리스트 조회
    override suspend fun getAllFavoriteList(
        data: GetFavoriteListRequest
    ): NetworkResult<FavoriteListData> {
        return handleResult {
            favoriteApi.getAllFavoriteList(
                page = data.page,
                size = data.size
            )
        }
    }

    // 좋아요 토글
    override suspend fun toggleFavorite(
        data: ToggleFavoriteRequest
    ): NetworkResult<ToggleFavoriteData> {
        return handleResult {
            favoriteApi.toggleFavorite(
                body = data
            )
        }
    }
}