package com.jeju.nanaland.domain.request.restaurant

import com.google.gson.annotations.SerializedName

data class GetRestaurantContentRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("isSearch")
    val isSearch: Boolean
)
