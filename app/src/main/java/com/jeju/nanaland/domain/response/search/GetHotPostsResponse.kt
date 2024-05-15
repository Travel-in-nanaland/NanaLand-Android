package com.jeju.nanaland.domain.response.search

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.search.HotPostThumbnailData

data class GetHotPostsResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: List<HotPostThumbnailData>
)
