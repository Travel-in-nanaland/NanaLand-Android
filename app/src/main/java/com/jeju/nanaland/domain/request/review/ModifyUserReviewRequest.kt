package com.jeju.nanaland.domain.request.review

import com.google.gson.annotations.SerializedName

data class ModifyUserReviewRequest(
    @SerializedName("id")
    val id: Int
)
