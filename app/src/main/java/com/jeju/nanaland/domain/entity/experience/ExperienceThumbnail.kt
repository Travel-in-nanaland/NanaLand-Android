package com.jeju.nanaland.domain.entity.experience

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class ExperienceThumbnail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("firstImage")
    val firstImage: ImageUrl?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("addressTag")
    val addressTag: String?,
    @SerializedName("ratingAvg")
    val ratingAvg: Double,
    @SerializedName("favorite")
    val favorite: Boolean
)
