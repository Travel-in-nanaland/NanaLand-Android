package com.jeju.nanaland.domain.response.search

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.search.SearchResultData

data class GetAllSearchResultListResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: AllSearchResultListData
)

data class AllSearchResultListData(
    @SerializedName("festival")
    val festival: SearchResultData,
    @SerializedName("nature")
    val nature: SearchResultData,
    @SerializedName("activity")
    val activity: SearchResultData,
    @SerializedName("cultureAndArts")
    val art: SearchResultData,
    @SerializedName("market")
    val market: SearchResultData,
    @SerializedName("restaurant")
    val restaurant: SearchResultData,
    @SerializedName("nana")
    val nana: SearchResultData
)