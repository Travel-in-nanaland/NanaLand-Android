package com.jeju.nanaland.domain.request.review

import com.google.gson.annotations.SerializedName

data class GetReviewListByPostRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)
