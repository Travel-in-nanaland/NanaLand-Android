package com.jeju.nanaland.domain.request.review

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType

data class GetReviewListByPostRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: ReviewCategoryType,
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)
