package com.jeju.nanaland.domain.request.member

import com.google.gson.annotations.SerializedName

data class UpdateLanguageRequest(
    @SerializedName("locale")
    val locale: String
)
