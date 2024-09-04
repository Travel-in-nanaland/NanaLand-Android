package com.jeju.nanaland.domain.entity.report

data class ReportDetail(
    val id: Int,
    val reportType: ReportType,
    val claimType: ClaimType,
    val content: String,
    val email: String
)
