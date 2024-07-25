package com.jeju.nanaland.domain.request.search

import com.google.gson.annotations.SerializedName

data class GetSearchResultListRequest(
    @SerializedName("keyword")
    val keyword: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)