package com.jeju.nanaland.domain.request.nanapick

import com.google.gson.annotations.SerializedName

data class GetNanaPickContentRequest(
    @SerializedName("id")
    val id: Int
)