package com.jeju.nanaland.domain.response.search

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.search.SearchResultData

data class GetSearchResultListResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: SearchResultData
)