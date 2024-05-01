package com.nanaland.domain.request.member

import com.google.gson.annotations.SerializedName

data class UpdateUserTypeRequest(
    @SerializedName("type")
    val type: String
)
