package com.jeju.nanaland.domain.entity.review

import com.google.gson.annotations.SerializedName

data class ReviewListData(
    @SerializedName("totalElements")
    val totalElements: Int,
    @SerializedName("totalAvgRating")
    val totalAvgRating: Double,
    @SerializedName("data")
    val data: List<ReviewData>
)
