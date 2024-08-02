package com.jeju.nanaland.domain.request.review

import com.google.gson.annotations.SerializedName

data class GetReviewByUserRequest(
    @SerializedName("memberId")
    val memberId: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)
