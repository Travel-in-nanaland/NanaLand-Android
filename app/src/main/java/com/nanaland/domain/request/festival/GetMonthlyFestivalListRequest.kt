package com.nanaland.domain.request.festival

data class GetMonthlyFestivalListRequest(
    val page: Long,
    val size: Long,
    val addressFilterList: List<String>,
    val startDate: String?,
    val endDate: String?
)