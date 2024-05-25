package com.jeju.nanaland.domain.entity.member

import com.google.gson.annotations.SerializedName

data class RecommendedPostData(
    @SerializedName("id")
    val id: Long,
    @SerializedName("category")
    val category: String?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("introduction")
    val intro: String?
)
