package com.jeju.nanaland.domain.entity.restaurant

import com.google.gson.annotations.SerializedName

data class RestaurantThumbnailListData(
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("data")
    val data: List<RestaurantThumbnailData>
)
