package com.jeju.nanaland.domain.entity.review

import com.google.gson.annotations.SerializedName

data class ReviewFavorite(
    @SerializedName("reviewHeart")
    val reviewHeart: Boolean
)
