package com.nanaland.domain.entity.search

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("totalElements")
    val count: Long,
    @SerializedName("data")
    val data: List<SearchResultThumbnail>
)
