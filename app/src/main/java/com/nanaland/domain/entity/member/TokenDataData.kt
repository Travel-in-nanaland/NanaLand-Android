package com.nanaland.domain.entity.member

import com.google.gson.annotations.SerializedName

data class TokenDataData(
    @SerializedName("accessToken")
    val accessToken: String?,
    @SerializedName("refreshToken")
    val refreshToken: String?
)
