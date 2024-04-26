package com.nanaland.domain.response.search

import com.google.gson.annotations.SerializedName

data class GetAllSearchResultListResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: GetAllSearchResultListResponseData
)

data class GetAllSearchResultListResponseData(
    @SerializedName("festival")
    val festival: GetSearchResultListData,
    @SerializedName("nature")
    val nature: GetSearchResultListData,
    @SerializedName("experience")
    val experience: GetSearchResultListData,
    @SerializedName("market")
    val market: GetSearchResultListData,
)