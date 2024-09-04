package com.jeju.nanaland.domain.entity.report

import com.google.gson.annotations.SerializedName

enum class ReportType {
    @SerializedName("MEMBER")
    MEMBER,
    @SerializedName("REVIEW")
    REVIEW
}