package com.jeju.nanaland.domain.entity.experience

import com.google.gson.annotations.SerializedName

data class ExperienceThumbnailListData(
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("data")
    val data: List<ExperienceThumbnailData>
)
