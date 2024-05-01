package com.nanaland.domain.response.member

import com.google.gson.annotations.SerializedName

data class UpdateUserTypeResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: Any?
)
