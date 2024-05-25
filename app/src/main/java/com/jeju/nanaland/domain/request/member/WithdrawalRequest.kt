package com.jeju.nanaland.domain.request.member

import com.google.gson.annotations.SerializedName

data class WithdrawalRequest(
    @SerializedName("withdrawalType")
    val withdrawalType: String
)
