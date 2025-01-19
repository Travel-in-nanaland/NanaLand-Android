package com.jeju.nanaland.domain.entity.report

import com.google.gson.annotations.SerializedName

data class ReportDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("reportType")
    val reportType: ReportType,
    @SerializedName("claimType")
    val claimType: ClaimType,
    @SerializedName("content")
    val content: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("fileKeys")
    val images: List<String>?,
)
