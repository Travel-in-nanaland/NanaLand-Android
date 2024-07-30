package com.jeju.nanaland.domain.entity.favorite

import com.google.gson.annotations.SerializedName

data class ToggleFavoriteData(
    @SerializedName("reviewHeart")
    val favorite: Boolean
)
