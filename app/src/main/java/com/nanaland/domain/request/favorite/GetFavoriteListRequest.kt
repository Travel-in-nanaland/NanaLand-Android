package com.nanaland.domain.request.favorite

import com.google.gson.annotations.SerializedName

data class GetFavoriteListRequest(
    @SerializedName("page")
    val page: Long,
    @SerializedName("size")
    val size: Long
)