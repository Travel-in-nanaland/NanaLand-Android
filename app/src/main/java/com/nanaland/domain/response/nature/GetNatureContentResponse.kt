package com.nanaland.domain.response.nature

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.nature.NatureContent

data class GetNatureContentResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: NatureContent
)
