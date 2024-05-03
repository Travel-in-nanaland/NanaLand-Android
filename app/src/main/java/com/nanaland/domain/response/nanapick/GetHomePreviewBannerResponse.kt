package com.nanaland.domain.response.nanapick

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.nanapick.NanaPickBannerData

data class GetHomePreviewBannerResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<NanaPickBannerData>
)