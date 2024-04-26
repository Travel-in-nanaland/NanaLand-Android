package com.nanaland.domain.entity.nanapick

import com.google.gson.annotations.SerializedName

data class NanaPickSubContentAdditionalInfo(
    @SerializedName("infoKey")
    val infoKey: String?,
    @SerializedName("infoValue")
    val infoValue: String?
)
