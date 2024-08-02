package com.jeju.nanaland.domain.entity.auth

import com.google.gson.annotations.SerializedName

data class AuthTokenData(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)
