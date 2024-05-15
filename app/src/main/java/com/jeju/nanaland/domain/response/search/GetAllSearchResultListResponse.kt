package com.jeju.nanaland.domain.response.search

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.search.SearchResultData

data class GetAllSearchResultListResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: GetAllSearchResultListResponseData
)

data class GetAllSearchResultListResponseData(
    @SerializedName("festival")
    val festival: SearchResultData,
    @SerializedName("nature")
    val nature: SearchResultData,
    @SerializedName("experience")
    val experience: SearchResultData,
    @SerializedName("market")
    val market: SearchResultData,
)