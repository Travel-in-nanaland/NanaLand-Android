package com.nanaland.domain.request.favorite

import com.google.gson.annotations.SerializedName

data class ToggleFavoriteRequest(
    @SerializedName("id")
    val id: Long,
    @SerializedName("category")
    val category: String
)
