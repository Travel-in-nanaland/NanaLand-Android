package com.jeju.nanaland.domain.entity.notice

import com.google.gson.annotations.SerializedName

data class NoticeSummery(
    @SerializedName("id")
    val id: Int,
    @SerializedName("noticeCategory")
    val noticeCategory: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("createdAt")
    val createdAt: String,
)

