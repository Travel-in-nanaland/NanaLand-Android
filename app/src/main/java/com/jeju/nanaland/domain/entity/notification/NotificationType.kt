package com.jeju.nanaland.domain.entity.notification

import com.google.gson.annotations.SerializedName

enum class NotificationType {
    @SerializedName("NANA")
    NANA,
    @SerializedName("NATURE")
    NATURE,
    @SerializedName("FESTIVAL")
    FESTIVAL,
    @SerializedName("MARKET")
    MARKET,
    @SerializedName("EXPERIENCE")
    EXPERIENCE,
    @SerializedName("RESTAURANT")
    RESTAURANT,
    @SerializedName("NOTICE")
    NOTICE,
    @SerializedName("ETC")
    ETC
}