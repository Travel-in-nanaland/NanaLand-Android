package com.jeju.nanaland.domain.request.auth

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.member.ConsentItem
import com.jeju.nanaland.globalvalue.type.LanguageType

data class SignUpRequest (
    @SerializedName("consentItems")
    val consentItems: List<ConsentItem>?,
    @SerializedName("email")
    val email: String,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("providerId")
    val providerId: String,
    @SerializedName("locale")
    val locale: LanguageType,
    @SerializedName("nickname")
    val nickname: String
)