package com.jeju.nanaland.domain.entity.festival

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class FestivalContentData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<ImageUrl>,
    @SerializedName("addressTag")
    val addressTag: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("contact")
    val contact: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("fee")
    val fee: String,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("period")
    val period: String,
    @SerializedName("favorite")
    val favorite: Boolean
)
