package com.jeju.nanaland.domain.request.restaurant

import com.google.gson.annotations.SerializedName

data class GetRestaurantListRequest(
    @SerializedName("keywordFilterList")
    val keywordFilterList: List<String>,
    @SerializedName("addressFilterList")
    val addressFilterList: List<String>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)
