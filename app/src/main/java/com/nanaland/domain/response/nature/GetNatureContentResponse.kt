package com.nanaland.domain.response.nature

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.nature.NatureContentData

data class GetNatureContentResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: NatureContentData
)
