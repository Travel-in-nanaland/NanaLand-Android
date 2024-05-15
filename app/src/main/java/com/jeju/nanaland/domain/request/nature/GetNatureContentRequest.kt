package com.jeju.nanaland.domain.request.nature

data class GetNatureContentRequest(
    val id: Long,
    val isSearch: Boolean
)