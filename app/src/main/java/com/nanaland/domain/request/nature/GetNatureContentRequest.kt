package com.nanaland.domain.request.nature

import com.google.gson.annotations.SerializedName

data class GetNatureContentRequest(
    val id: Long,
    val isSearch: Boolean
)