package com.jeju.nanaland.domain.request.search

import com.google.gson.annotations.SerializedName

data class GetAllSearchResultListRequest(
    @SerializedName("keyword")
    val keyword: String
)