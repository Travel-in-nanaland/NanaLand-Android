package com.nanaland.domain.response.market

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.market.MarketThumbnail

data class GetMarketListResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: GetMarketListResponseData
)

data class GetMarketListResponseData(
    @SerializedName("totalElements")
    val totalElements: Long,
    @SerializedName("data")
    val data: List<MarketThumbnail>
)