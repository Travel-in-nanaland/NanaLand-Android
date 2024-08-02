package com.jeju.nanaland.domain.request.review

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.globalvalue.type.ReviewKeyword

data class CreateReviewRequest(
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("reviewKeywords")
    val keywords: List<ReviewKeyword>
)