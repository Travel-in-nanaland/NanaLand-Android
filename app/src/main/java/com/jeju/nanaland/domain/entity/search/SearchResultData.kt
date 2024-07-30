package com.jeju.nanaland.domain.entity.search

import com.google.gson.annotations.SerializedName

data class SearchResultData(
    @SerializedName("totalElements")
    val count: Int,
    @SerializedName("data")
    val data: List<SearchResultThumbnailData>
)
