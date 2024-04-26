package com.nanaland.domain.request.market

import com.google.gson.annotations.SerializedName

data class GetMarketListRequest(
    @SerializedName("page")
    val page: Long,
    @SerializedName("size")
    val size: Long
)