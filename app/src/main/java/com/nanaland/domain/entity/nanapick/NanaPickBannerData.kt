package com.nanaland.domain.entity.nanapick

import com.google.gson.annotations.SerializedName

data class NanaPickBannerData(
    @SerializedName("id")
    val id: Long,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("version")
    val version: String?,
    @SerializedName("subHeading")
    val subHeading: String?,
    @SerializedName("heading")
    val heading: String?,
)