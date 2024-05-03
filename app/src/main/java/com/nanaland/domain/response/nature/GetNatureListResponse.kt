package com.nanaland.domain.response.nature

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.nature.NatureThumbnailData

data class GetNatureListResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: GetNatureListResponseData
)

data class GetNatureListResponseData(
    @SerializedName("totalElements")
    val totalElements: Long,
    @SerializedName("data")
    val data: List<NatureThumbnailData>
)
