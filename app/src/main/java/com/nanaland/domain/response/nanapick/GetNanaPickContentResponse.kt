package com.nanaland.domain.response.nanapick

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.nanapick.NanaPickContentData

data class GetNanaPickContentResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: NanaPickContentData
)