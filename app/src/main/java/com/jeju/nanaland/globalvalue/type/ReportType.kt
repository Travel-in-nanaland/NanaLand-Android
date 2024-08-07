package com.jeju.nanaland.globalvalue.type

import com.google.gson.annotations.SerializedName

enum class ReportType(val stringIndex: Int) {
    @SerializedName("COMMERCIAL_PURPOSE")
    COMMERCIAL_PURPOSE(0),
    @SerializedName("DISLIKE")
    DISLIKE(1),
    @SerializedName("PROFANITY")
    PROFANITY(2),
    @SerializedName("PERSONAL_INFORMATION")
    PERSONAL_INFORMATION(3),
    @SerializedName("OBSCENITY")
    OBSCENITY(4),
    @SerializedName("FACILITY_ISSUE")
    FACILITY_ISSUE(5),
    @SerializedName("DRUGS")
    DRUGS(6),
    @SerializedName("VIOLENCE")
    VIOLENCE(7),
    @SerializedName("ETC")
    ETC(8)
}