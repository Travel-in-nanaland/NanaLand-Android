package com.nanaland.domain.response.favorite

import com.google.gson.annotations.SerializedName
import com.nanaland.domain.entity.favorite.FavoriteThumbnail

data class GetFavoriteListResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: GetFavoriteListResponseData
)

data class GetFavoriteListResponseData(
    @SerializedName("totalElements")
    val totalElements: Long,
    @SerializedName("data")
    val data: List<FavoriteThumbnail>
)