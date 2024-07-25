package com.jeju.nanaland.domain.entity.review

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class ReviewData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("memberId")
    val memberId: Int,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("profileImage")
    val profileImage: ImageUrl?,
    @SerializedName("memberReviewCount")
    val memberReviewCount: Int,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("content")
    val content: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("heartCount")
    val heartCount: Int,
    @SerializedName("images")
    val images: List<ImageUrl?>,
    @SerializedName("reviewTypeKeywords")
    val reviewTypeKeywords: List<String>,
    @SerializedName("reviewHeart")
    val reviewHeart: Boolean
)
