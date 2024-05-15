package com.jeju.nanaland.domain.response.festival

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.festival.FestivalThumbnailData

data class GetEndedFestivalListResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: GetEndedFestivalListResponseData
)

data class GetEndedFestivalListResponseData(
    @SerializedName("totalElements")
    val totalElements: Long,
    @SerializedName("data")
    val data: List<FestivalThumbnailData>
)