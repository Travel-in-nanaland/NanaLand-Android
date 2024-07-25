package com.jeju.nanaland.domain.entity.market

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.market.MarketThumbnail

data class MarketThumbnailListData(
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("data")
    val data: List<MarketThumbnail>
)