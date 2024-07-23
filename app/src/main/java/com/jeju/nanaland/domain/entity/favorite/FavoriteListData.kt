package com.jeju.nanaland.domain.entity.favorite

import com.google.gson.annotations.SerializedName

data class FavoriteListData(
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("data")
    val data: List<FavoriteThumbnailData>
)