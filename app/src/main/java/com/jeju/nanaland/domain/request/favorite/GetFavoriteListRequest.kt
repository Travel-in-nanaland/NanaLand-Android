package com.jeju.nanaland.domain.request.favorite

import com.google.gson.annotations.SerializedName

data class GetFavoriteListRequest(
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)