package com.jeju.nanaland.domain.entity.restaurant

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class FoodMenu(
    @SerializedName("menuName")
    val menuName: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("firstImage")
    val firstImage: ImageUrl
)
