package com.jeju.nanaland.domain.entity.festival

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class FestivalThumbnailData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("firstImage")
    val firstImage: ImageUrl,
    @SerializedName("addressTag")
    val addressTag: String,
    @SerializedName("period")
    val period: String,
    @SerializedName("favorite")
    val favorite: Boolean
)
