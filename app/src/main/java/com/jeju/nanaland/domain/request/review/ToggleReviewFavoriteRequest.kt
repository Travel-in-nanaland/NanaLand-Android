package com.jeju.nanaland.domain.request.review

import com.google.gson.annotations.SerializedName

data class ToggleReviewFavoriteRequest(
    @SerializedName("id")
    val id: Int
)
