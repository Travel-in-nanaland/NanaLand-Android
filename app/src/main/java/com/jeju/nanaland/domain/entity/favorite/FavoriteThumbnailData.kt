package com.jeju.nanaland.domain.entity.favorite

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class FavoriteThumbnailData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("firstImage")
    val firstImage: ImageUrl?,
    @SerializedName("title")
    val title: String,
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("onGoing")
    val isNotOver: Boolean?
)
