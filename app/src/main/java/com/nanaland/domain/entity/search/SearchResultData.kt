package com.nanaland.domain.entity.search

import com.google.gson.annotations.SerializedName

data class SearchResultData(
    @SerializedName("totalElements")
    val count: Long,
    @SerializedName("data")
    val data: List<SearchResultThumbnailData>
)
