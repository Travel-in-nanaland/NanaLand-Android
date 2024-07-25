package com.jeju.nanaland.domain.entity.market

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class MarketContent(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("addressTag")
    val addressTag: String?,
    @SerializedName("contact")
    val contact: String?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("amenity")
    val amenity: String?,
    @SerializedName("images")
    val images: List<ImageUrl?>,
    @SerializedName("favorite")
    val favorite: Boolean
)
