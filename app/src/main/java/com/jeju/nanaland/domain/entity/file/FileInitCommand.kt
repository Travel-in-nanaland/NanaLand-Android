package com.jeju.nanaland.domain.entity.file

import com.google.gson.annotations.SerializedName

data class FileInitCommand (
    @SerializedName("originalFileName")
    val name: String, // 확장자 포함 파일명
    @SerializedName("fileSize")
    val size: Long, // 파일 사이즈
    @SerializedName("partCount")
    val partCount: Int, // 파일 청크 개수
    @SerializedName("fileCategory")
    val category: FileCategory
)

enum class FileCategory {
    @SerializedName("MEMBER_PROFILE")
    Profile,
    @SerializedName("REVIEW")
    Review,
    @SerializedName("INFO_FIX_REPORT")
    InfoFixReport,
    @SerializedName("CLAIM_REPORT")
    ClaimReport,
}