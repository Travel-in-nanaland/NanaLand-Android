package com.nanaland.domain.response.member

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.member.RecommendedPostData

data class GetRecommendedPostResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<RecommendedPostData>
)
