package com.jeju.nanaland.domain.entity.festival

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.festival.FestivalThumbnailData

data class SeasonalFestivalListData(
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("data")
    val data: List<FestivalThumbnailData>
)