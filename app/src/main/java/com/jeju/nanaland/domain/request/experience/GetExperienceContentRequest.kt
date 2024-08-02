package com.jeju.nanaland.domain.request.experience

import com.google.gson.annotations.SerializedName

data class GetExperienceContentRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("isSearch")
    val isSearch: Boolean
)
