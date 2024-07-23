package com.jeju.nanaland.domain.request.nature

import com.google.gson.annotations.SerializedName

data class GetNatureListRequest(
    @SerializedName("addressFilterList")
    val addressFilterList: List<String>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("size")
    val size: Int
)