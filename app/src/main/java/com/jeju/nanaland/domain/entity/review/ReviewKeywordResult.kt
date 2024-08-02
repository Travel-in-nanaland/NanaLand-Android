package com.jeju.nanaland.domain.entity.review

import androidx.annotation.StyleRes
import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class ReviewKeywordResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("categoryValue")
    val categoryValue: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("firstImage")
    val firstImage: ImageUrl,
    @SerializedName("address")
    val address: String,
)
