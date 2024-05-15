package com.jeju.nanaland.domain.entity.search

import com.google.gson.annotations.SerializedName

data class HotPostThumbnailData(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("favorite")
    val favorite: Boolean
)
