package com.nanaland.domain.entity.nature

import com.google.gson.annotations.SerializedName

data class NatureThumbnail(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("addressTag")
    val addressTag: String?,
    @SerializedName("favorite")
    val favorite: Boolean
)
