package com.nanaland.domain.entity.nanapick

import com.google.gson.annotations.SerializedName

data class NanaPickSubContent(
    @SerializedName("number")
    val number: Long,
    @SerializedName("subTitle")
    val subTitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("additionalInfoList")
    val nanaPickSubContentAdditionalInfoList: List<NanaPickSubContentAdditionalInfo>,
    @SerializedName("hashtags")
    val hashtags: List<String?>
)