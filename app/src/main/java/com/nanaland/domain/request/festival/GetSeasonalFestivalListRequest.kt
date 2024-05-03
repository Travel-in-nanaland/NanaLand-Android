package com.nanaland.domain.request.festival

data class GetSeasonalFestivalListRequest(
    val page: Long,
    val size: Long,
    val season: String
)
