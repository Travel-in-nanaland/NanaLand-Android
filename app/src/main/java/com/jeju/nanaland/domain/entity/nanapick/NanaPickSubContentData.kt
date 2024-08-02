package com.jeju.nanaland.domain.entity.nanapick

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class NanaPickSubContentData(
    @SerializedName("number")
    val number: Int,
    @SerializedName("subTitle")
    val subTitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("images")
    val images: List<ImageUrl>,
    @SerializedName("content")
    val content: String,
    @SerializedName("additionalInfoList")
    val nanaPickSubContentAdditionalInfoList: List<NanaPickSubContentAdditionalInfoData>,
    @SerializedName("hashtags")
    val hashtags: List<String>
)