package com.jeju.nanaland.domain.request.review

import com.google.gson.annotations.SerializedName

data class DeleteReviewRequest(
    @SerializedName("id")
    val id: Int
)
