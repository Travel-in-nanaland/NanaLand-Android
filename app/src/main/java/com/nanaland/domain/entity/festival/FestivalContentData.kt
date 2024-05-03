package com.nanaland.domain.entity.festival

import com.google.gson.annotations.SerializedName

data class FestivalContentData(
    @SerializedName("id")
    val id: Long,
    @SerializedName("originUrl")
    val originUrl: String?,
    @SerializedName("addressTag")
    val addressTag: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("contact")
    val contact: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("fee")
    val fee: String?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("period")
    val period: String?,
    @SerializedName("favorite")
    val favorite: Boolean
)
