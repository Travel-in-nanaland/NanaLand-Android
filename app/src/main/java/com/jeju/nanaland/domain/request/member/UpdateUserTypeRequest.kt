package com.jeju.nanaland.domain.request.member

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.globalvalue.constant.TravelType

data class UpdateUserTypeRequest(
    @SerializedName("type")
    val type: TravelType
)
