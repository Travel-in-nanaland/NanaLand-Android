package com.jeju.nanaland.domain.entity.festival

import com.google.gson.annotations.SerializedName

data class EndedFestivalListData(
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("data")
    val data: List<FestivalThumbnailData>
)