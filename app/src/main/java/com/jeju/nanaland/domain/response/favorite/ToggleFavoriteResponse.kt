package com.jeju.nanaland.domain.response.favorite

import com.google.gson.annotations.SerializedName

data class ToggleFavoriteResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: ToggleFavoriteResponseData
)

data class ToggleFavoriteResponseData(
    @SerializedName("favorite")
    val favorite: Boolean
)
