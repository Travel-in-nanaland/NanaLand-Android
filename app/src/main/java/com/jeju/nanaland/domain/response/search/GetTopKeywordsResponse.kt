package com.jeju.nanaland.domain.response.search

import com.google.gson.annotations.SerializedName

data class GetTopKeywordsResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<String>
)
