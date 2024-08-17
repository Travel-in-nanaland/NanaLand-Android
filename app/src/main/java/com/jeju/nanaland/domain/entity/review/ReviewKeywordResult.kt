package com.jeju.nanaland.domain.entity.review

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType

data class ReviewKeywordResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: ReviewCategoryType,
    @SerializedName("categoryValue")
    val categoryValue: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("firstImage")
    val firstImage: ImageUrl,
    @SerializedName("address")
    val address: String,
)
