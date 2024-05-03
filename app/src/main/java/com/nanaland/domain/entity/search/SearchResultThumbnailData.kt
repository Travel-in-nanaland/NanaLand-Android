package com.nanaland.domain.entity.search

import com.google.gson.annotations.SerializedName

data class SearchResultThumbnailData(
    @SerializedName("id")
    val id: Long,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("favorite")
    val favorite: Boolean
)