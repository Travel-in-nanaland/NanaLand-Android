package com.jeju.nanaland.domain.request.review

import com.google.gson.annotations.SerializedName

data class GetReviewThumbnailListByUserRequest(
    @SerializedName("memberId")
    val memberId: Int
)
