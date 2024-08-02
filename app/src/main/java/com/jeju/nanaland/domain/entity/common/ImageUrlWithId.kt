package com.jeju.nanaland.domain.entity.common

import com.google.gson.annotations.SerializedName

data class ImageUrlWithId(
    @SerializedName("id")
    val id: Int,
    @SerializedName("originUrl")
    val originUrl: String,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
)
