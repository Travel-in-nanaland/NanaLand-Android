package com.jeju.nanaland.domain.entity.notification

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Notification(
    @SerializedName("notificationId")
    val notificationId: Int,
    @SerializedName("clickEvent")
    val clickEvent: NotificationLinkType,
    @SerializedName("category")
    val category: NotificationType,
    @SerializedName("contentId")
    val contentId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: Date,
)
