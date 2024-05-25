package com.jeju.nanaland.domain.request.member

import com.google.gson.annotations.SerializedName

data class UpdateUserProfileRequest(
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("description")
    val description: String
)
