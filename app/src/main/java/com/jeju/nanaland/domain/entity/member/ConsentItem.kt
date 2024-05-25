package com.jeju.nanaland.domain.entity.member

import com.google.gson.annotations.SerializedName

data class ConsentItem(
    @SerializedName("consentType")
    val consentType: String,
    @SerializedName("consent")
    val consent: Boolean
)
