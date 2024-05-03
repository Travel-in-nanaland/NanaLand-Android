package com.nanaland.domain.response.search

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.search.SearchResultData

data class GetSearchResultListResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: SearchResultData
)