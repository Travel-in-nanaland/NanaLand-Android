package com.nanaland.domain.entity.favorite

import com.google.gson.annotations.SerializedName

data class FavoriteThumbnailData(
    @SerializedName("id")
    val id: Long,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("category")
    val category: String?,
)
