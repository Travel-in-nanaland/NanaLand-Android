package com.jeju.nanaland.domain.response

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<T>(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: T
)
