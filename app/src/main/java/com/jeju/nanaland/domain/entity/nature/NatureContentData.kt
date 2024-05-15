package com.jeju.nanaland.domain.entity.nature

import com.google.gson.annotations.SerializedName

data class NatureContentData(
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
    @SerializedName("intro")
    val intro: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("contact")
    val contact: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("fee")
    val fee: String?,
    @SerializedName("details")
    val details: String?,
    @SerializedName("amenity")
    val amenity: String?,
    @SerializedName("favorite")
    val favorite: Boolean
)