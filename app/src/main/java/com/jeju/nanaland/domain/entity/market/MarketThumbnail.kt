package com.jeju.nanaland.domain.entity.market

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class MarketThumbnail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("firstImage")
    val firstImage: ImageUrl?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("addressTag")
    val addressTag: String?,
    @SerializedName("favorite")
    val favorite: Boolean
)