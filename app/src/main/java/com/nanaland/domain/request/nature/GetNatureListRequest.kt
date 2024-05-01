package com.nanaland.domain.request.nature

import com.google.gson.annotations.SerializedName

data class GetNatureListRequest(
    @SerializedName("addressFilterList")
    val addressFilterList: String,
    @SerializedName("page")
    val page: Long,
    @SerializedName("size")
    val size: Long
)