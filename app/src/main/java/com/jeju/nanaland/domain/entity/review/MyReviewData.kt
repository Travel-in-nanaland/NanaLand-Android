package com.jeju.nanaland.domain.entity.review

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl
import com.jeju.nanaland.domain.entity.common.ImageUrlWithId

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
    @SerializedName("content")
    val content: String,
    @SerializedName("images")
    val images: ImageUrlWithId,
    @SerializedName("reviewKeywords")
    val reviewKeywords: List<String>
)
