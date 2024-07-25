package com.jeju.nanaland.domain.request.festival

data class GetSeasonalFestivalListRequest(
    val page: Int,
    val size: Int,
    val season: String
)
