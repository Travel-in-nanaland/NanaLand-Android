package com.jeju.nanaland.domain.request.auth

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("locale")
    val locale: String,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("providerId")
    val providerId: String
)
