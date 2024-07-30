package com.jeju.nanaland.domain.request.market

import com.google.gson.annotations.SerializedName

data class GetMarketListRequest(
    @SerializedName("addressFilterList")
    val addressFilterList: List<String>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)