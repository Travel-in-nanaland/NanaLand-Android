package com.jeju.nanaland.domain.entity.search

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class SearchResultThumbnailData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("firstImage")
    val firstImage: ImageUrl,
    @SerializedName("title")
    val title: String,
    @SerializedName("favorite")
    val favorite: Boolean
)