package com.jeju.nanaland.domain.entity.nature

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class NatureThumbnail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("firstImage")
    val firstImage: ImageUrl?,
    @SerializedName("addressTag")
    val addressTag: String?,
    @SerializedName("favorite")
    val favorite: Boolean
)
