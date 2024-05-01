package com.nanaland.domain.response.search

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.search.SearchResult

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
    val festival: SearchResult,
    @SerializedName("nature")
    val nature: SearchResult,
    @SerializedName("experience")
    val experience: SearchResult,
    @SerializedName("market")
    val market: SearchResult,
)