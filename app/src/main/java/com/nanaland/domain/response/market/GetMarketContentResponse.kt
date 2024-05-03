package com.nanaland.domain.response.market

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.market.MarketContentData

data class GetMarketContentResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: MarketContentData
)
