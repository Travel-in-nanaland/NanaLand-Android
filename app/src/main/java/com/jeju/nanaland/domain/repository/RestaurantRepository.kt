package com.jeju.nanaland.domain.repository

import android.net.Network
import com.jeju.nanaland.domain.entity.restaurant.RestaurantContentData
import com.jeju.nanaland.domain.entity.restaurant.RestaurantThumbnailListData
import com.jeju.nanaland.domain.request.restaurant.GetRestaurantContentRequest
import com.jeju.nanaland.domain.request.restaurant.GetRestaurantListRequest
import com.jeju.nanaland.util.network.NetworkResult

interface RestaurantRepository {

    // 제주 맛집 상세 정보 조회
    suspend fun getRestaurantContent(
        data: GetRestaurantContentRequest
    ): NetworkResult<RestaurantContentData>

    // 제주 맛집 리스트 조회
    suspend fun getRestaurantList(
        data: GetRestaurantListRequest
    ): NetworkResult<RestaurantThumbnailListData>
}