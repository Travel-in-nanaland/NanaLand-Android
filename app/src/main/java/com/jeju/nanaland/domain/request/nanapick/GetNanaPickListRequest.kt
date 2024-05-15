package com.jeju.nanaland.domain.request.nanapick

import com.google.gson.annotations.SerializedName

data class GetNanaPickListRequest(
    @SerializedName("page")
    val page: Long,
    @SerializedName("size")
    val size: Long
)