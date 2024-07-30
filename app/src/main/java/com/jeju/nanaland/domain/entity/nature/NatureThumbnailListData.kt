package com.jeju.nanaland.domain.entity.nature

import com.google.gson.annotations.SerializedName

data class NatureThumbnailListData(
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("data")
    val data: List<NatureThumbnail>
)
