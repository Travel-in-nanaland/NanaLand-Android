package com.jeju.nanaland.domain.entity.market

import com.google.gson.annotations.SerializedName

data class MarketContentData(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String?,
    @SerializedName("originUrl")
    val originUrl: String?,
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
    @SerializedName("favorite")
    val favorite: Boolean
)
