package com.jeju.nanaland.domain.entity.nanapick

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class NanaPickBannerData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("firstImage")
    val firstImage: ImageUrl,
    @SerializedName("version")
    val version: String,
    @SerializedName("subHeading")
    val subHeading: String,
    @SerializedName("heading")
    val heading: String,
    @SerializedName("newest")
    val newest: Boolean?
)