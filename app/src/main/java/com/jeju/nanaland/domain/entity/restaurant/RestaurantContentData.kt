package com.jeju.nanaland.domain.entity.restaurant

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class RestaurantContentData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("addressTag")
    val addressTag: String?,
    @SerializedName("contact")
    val contact: String?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("instagram")
    val instagram: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("service")
    val service: String?,
    @SerializedName("menus")
    val menus: List<FoodMenu>,
    @SerializedName("keywords")
    val keywords: List<String>,
    @SerializedName("images")
    val images: List<ImageUrl>,
    @SerializedName("favorite")
    val favorite: Boolean
)
