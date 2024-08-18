package com.jeju.nanaland.domain.entity.review

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType

data class MemberReviewDetail (
    @SerializedName("id")
    val id: Int,
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("category")
    val category: ReviewCategoryType,
    @SerializedName("placeName")
    val placeName: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("heartCount")
    val heartCount: Int,
    @SerializedName("images")
    val images: List<ImageUrl>,//TODO
    @SerializedName("imageFileDto") //TODO
    val thumbnail: ImageUrl?,
    @SerializedName("reviewTypeKeywords")
    val reviewTypeKeywords: List<String>,
)
