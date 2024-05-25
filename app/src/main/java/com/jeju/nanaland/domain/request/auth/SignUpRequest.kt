package com.jeju.nanaland.domain.request.auth

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.member.ConsentItem

data class SignUpRequest (
    @SerializedName("consentItems")
    val consentItems: List<ConsentItem>,
    @SerializedName("email")
    val email: String,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("providerId")
    val providerId: String,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("nickname")
    val nickname: String
)