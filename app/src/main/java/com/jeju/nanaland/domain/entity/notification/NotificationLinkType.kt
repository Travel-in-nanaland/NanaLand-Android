package com.jeju.nanaland.domain.entity.notification

import com.google.gson.annotations.SerializedName

enum class NotificationLinkType {
    @SerializedName("POST")
    POST,
    @SerializedName("NOTICE")
    NOTICE,
    @SerializedName("NONE")
    NONE
}