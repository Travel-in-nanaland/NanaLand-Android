package com.nanaland.domain.entity.nanapick

import com.google.gson.annotations.SerializedName

data class NanaPickContent(
    @SerializedName("subHeading")
    val subHeading: String?,
    @SerializedName("heading")
    val heading: String?,
    @SerializedName("originUrl")
    val originUrl: String?,
    @SerializedName("notice")
    val notice: String?,
    @SerializedName("nanaDetails")
    val nanaDetails: List<NanaPickSubContent>
)
