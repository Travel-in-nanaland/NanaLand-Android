package com.jeju.nanaland.domain.entity.search

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class HotPostThumbnailData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("firstImage")
    val firstImage: ImageUrl,
    @SerializedName("category")
    val category: String,
    @SerializedName("favorite")
    val favorite: Boolean
)
