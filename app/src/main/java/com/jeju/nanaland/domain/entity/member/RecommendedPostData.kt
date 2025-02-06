package com.jeju.nanaland.domain.entity.member

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class RecommendedPostData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("firstImage")
    val firstImage: ImageUrl,
    @SerializedName("title")
    val title: String,
    @SerializedName("introduction")
    val intro: String?,
    @SerializedName("favorite")
    val favorite: Boolean
)
