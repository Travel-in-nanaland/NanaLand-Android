package com.nanaland.domain.request.nature

import com.google.gson.annotations.SerializedName

data class GetNatureContentRequest(
    @SerializedName("id")
    val id: Long
)