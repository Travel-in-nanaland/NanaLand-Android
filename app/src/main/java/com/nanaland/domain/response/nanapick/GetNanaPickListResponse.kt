package com.nanaland.domain.response.nanapick

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.nanapick.NanaPickThumbnail

data class GetNanaPickListResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: GetNanaPickListResponseData
)

data class GetNanaPickListResponseData(
    @SerializedName("totalElements")
    val totalElements: Long,
    @SerializedName("data")
    val data: List<NanaPickThumbnail>
)