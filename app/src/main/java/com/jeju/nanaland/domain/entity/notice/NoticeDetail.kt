package com.jeju.nanaland.domain.entity.notice

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class NoticeDetail(
    @SerializedName("title")
    val title: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("noticeContents")
    val noticeContents: List<NoticeContent>,
)

data class NoticeContent (
    @SerializedName("createdAt")
    val image: ImageUrl?,
    @SerializedName("content")
    val content: String?,
)