package com.jeju.nanaland.domain.request.festival

data class GetEndedFestivalListRequest(
    val page: Int,
    val size: Int,
    val addressFilterList: List<String>,
)
