package com.jeju.nanaland.domain.entity.nanapick

import com.google.gson.annotations.SerializedName

data class NanaPickSubContentAdditionalInfoData(
    @SerializedName("infoEmoji")
    val infoEmoji: String,
    @SerializedName("infoKey")
    val infoKey: String,
    @SerializedName("infoValue")
    val infoValue: String
)
