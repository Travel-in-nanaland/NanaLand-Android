package com.jeju.nanaland.domain.response.nanapick

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData

data class GetNanaPickListResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: GetNanaPickListResponseData
)

data class GetNanaPickListResponseData(
    @SerializedName("totalElements")
    val totalElements: Long,
    @SerializedName("data")
    val data: List<NanaPickBannerData>
)