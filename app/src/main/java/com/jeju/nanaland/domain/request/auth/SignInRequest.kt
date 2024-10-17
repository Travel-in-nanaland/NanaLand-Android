package com.jeju.nanaland.domain.request.auth

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.globalvalue.type.LanguageType

data class SignInRequest(
    @SerializedName("locale")
    val locale: LanguageType,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("providerId")
    val providerId: String,
    @SerializedName("fcmToken")
    val fcmToken: String?
)
