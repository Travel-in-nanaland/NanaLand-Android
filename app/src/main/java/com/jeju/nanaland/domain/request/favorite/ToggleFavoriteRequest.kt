package com.jeju.nanaland.domain.request.favorite

import com.google.gson.annotations.SerializedName

data class ToggleFavoriteRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: String
)
