package com.jeju.nanaland.domain.request.member

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.globalvalue.type.LanguageType

data class UpdateLanguageRequest(
    @SerializedName("locale")
    val locale: LanguageType
)
