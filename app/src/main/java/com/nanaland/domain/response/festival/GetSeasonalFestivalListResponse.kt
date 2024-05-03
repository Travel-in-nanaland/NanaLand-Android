package com.nanaland.domain.response.festival

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.festival.FestivalThumbnailData

data class GetSeasonalFestivalListResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: GetSeasonalFestivalListResponseData
)

data class GetSeasonalFestivalListResponseData(
    @SerializedName("totalElements")
    val totalElements: Long,
    @SerializedName("data")
    val data: List<FestivalThumbnailData>
)