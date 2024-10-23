package com.jeju.nanaland.domain.entity.nanapick

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class NanaPickContentData(
    @SerializedName("subHeading")
    val subHeading: String,
    @SerializedName("heading")
    val heading: String,
    @SerializedName("version")
    val version: String,
    @SerializedName("firstImage")
    val firstImage: ImageUrl,
    @SerializedName("notice")
    val notice: String?,
    @SerializedName("nanaDetails")
    val nanaDetails: List<NanaPickSubContentData>,
    @SerializedName("favorite")
    val favorite: Boolean
)
