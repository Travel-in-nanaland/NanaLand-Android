package com.jeju.nanaland.domain.response.auth

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.auth.AuthTokenData

data class ReissueAccessTokenResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: AuthTokenData
)
