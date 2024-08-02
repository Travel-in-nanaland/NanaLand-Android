package com.jeju.nanaland.domain.entity.favorite

import com.google.gson.annotations.SerializedName

data class ToggleFavoriteData(
    @SerializedName("favorite")
    val favorite: Boolean
)
