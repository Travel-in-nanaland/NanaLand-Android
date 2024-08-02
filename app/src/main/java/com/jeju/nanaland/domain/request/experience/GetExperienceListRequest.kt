package com.jeju.nanaland.domain.request.experience

import com.google.gson.annotations.SerializedName

data class GetExperienceListRequest(
    @SerializedName("experienceType")
    val experienceType: String,
    @SerializedName("keywordFilterList")
    val keywordFilterList: List<String>,
    @SerializedName("addressFilterList")
    val addressFilterList: List<String>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)
