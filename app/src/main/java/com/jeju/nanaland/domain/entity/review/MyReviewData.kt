package com.jeju.nanaland.domain.entity.review

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class MyReviewData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("firstImage")
    val firstImage: ImageUrl,
    @SerializedName("title")
    val title: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("rating")
    val rating: Double,

)
