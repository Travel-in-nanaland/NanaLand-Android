package com.nanaland.domain.response.festival

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.festival.FestivalContentData

data class GetFestivalContentResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: FestivalContentData
)
