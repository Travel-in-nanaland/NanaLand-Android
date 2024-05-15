package com.jeju.nanaland.domain.entity.festival

import com.google.gson.annotations.SerializedName

data class FestivalThumbnailData(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("addressTag")
    val addressTag: String?,
    @SerializedName("period")
    val period: String?,
    @SerializedName("favorite")
    val favorite: Boolean
)
