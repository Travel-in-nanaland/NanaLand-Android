package com.jeju.nanaland.domain.request.review

import com.google.gson.annotations.SerializedName

data class CreateReviewRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: String
)
