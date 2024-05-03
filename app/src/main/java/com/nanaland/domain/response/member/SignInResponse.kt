package com.nanaland.domain.response.member

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.member.TokenDataData

data class SignInResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: TokenDataData
)
