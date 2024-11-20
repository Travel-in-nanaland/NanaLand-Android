package com.jeju.nanaland.domain.entity.member

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class HotPostData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("firstImage")
    val firstImage: ImageUrl,
    @SerializedName("favorite")
    val favorite: Boolean
)
