package com.jeju.nanaland.domain.entity.experience

import com.google.gson.annotations.SerializedName

data class ExperienceContentImage(
    @SerializedName("originUrl")
    val originUrl: String?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?
)
