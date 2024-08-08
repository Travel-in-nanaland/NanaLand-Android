package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.RestaurantApi
import com.jeju.nanaland.domain.entity.restaurant.RestaurantContentData
import com.jeju.nanaland.domain.entity.restaurant.RestaurantThumbnailListData
import com.jeju.nanaland.domain.repository.RestaurantRepository
import com.jeju.nanaland.domain.request.restaurant.GetRestaurantContentRequest
import com.jeju.nanaland.domain.request.restaurant.GetRestaurantListRequest
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class RestaurantRepositoryImpl(
    private val restaurantApi: RestaurantApi
) : RestaurantRepository, NetworkResultHandler {

    // 제주 맛집 상세 정보 조회
    override suspend fun getRestaurantContent(
        data: GetRestaurantContentRequest
    ): NetworkResult<RestaurantContentData> {
        return handleResult {
            restaurantApi.getRestaurantContent(
                id = data.id,
                isSearch = data.isSearch
            )
        }
    }

    // 제주 맛집 리스트 조회
    override suspend fun getRestaurantList(
        data: GetRestaurantListRequest
    ): NetworkResult<RestaurantThumbnailListData> {
        return handleResult {
            restaurantApi.getRestaurantList(
                keywordFilterList = data.keywordFilterList,
                addressFilterList = data.addressFilterList,
                page = data.page,
                size = data.size
            )
        }
    }


}