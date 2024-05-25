package com.jeju.nanaland.domain.response.member

import com.google.gson.annotations.SerializedName

data class WithdrawalResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: String?
)
