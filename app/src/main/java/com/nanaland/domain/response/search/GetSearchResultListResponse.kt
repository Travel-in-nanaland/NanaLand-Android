package com.nanaland.domain.response.search

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.search.SearchResultThumbnail

data class GetSearchResultListResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: GetSearchResultListData
)

data class GetSearchResultListData(
    @SerializedName("totalElement")
    val count: Long,
    @SerializedName("data")
    val data: List<SearchResultThumbnail>
)