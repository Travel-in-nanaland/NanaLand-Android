package com.jeju.nanaland.domain.request.festival

data class GetMonthlyFestivalListRequest(
    val page: Int,
    val size: Int,
    val addressFilterList: List<String>,
    val startDate: String?,
    val endDate: String?
)