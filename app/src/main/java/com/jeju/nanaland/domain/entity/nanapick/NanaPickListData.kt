package com.jeju.nanaland.domain.entity.nanapick

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData

data class NanaPickListData(
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("data")
    val data: List<NanaPickBannerData>
)