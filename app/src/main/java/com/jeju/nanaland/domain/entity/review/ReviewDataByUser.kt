package com.jeju.nanaland.domain.entity.review

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class ReviewDataByUser(
    @SerializedName("id")
    val id: Int,
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("placeName")
    val placeName: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("heartCount")
    val heartCount: Int,
    @SerializedName("images")
    val images: ImageUrl,
    @SerializedName("reviewTypeKeywords")
    val reviewTypeKeywords: List<String>
)
