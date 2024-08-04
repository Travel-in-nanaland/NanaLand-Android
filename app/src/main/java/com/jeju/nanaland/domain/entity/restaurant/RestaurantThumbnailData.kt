package com.jeju.nanaland.domain.entity.restaurant

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class RestaurantThumbnailData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("firstImage")
    val firstImage: ImageUrl,
    @SerializedName("addressTag")
    val addressTag: String,
    @SerializedName("ratingAvg")
    val ratingAvg: Double,
    @SerializedName("favorite")
    val favorite: Boolean
)
