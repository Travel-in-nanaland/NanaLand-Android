package com.jeju.nanaland.globalvalue.type

import com.google.gson.annotations.SerializedName

enum class ReviewCategoryType {
    @SerializedName("NANA")
    NANA,
    @SerializedName("NANA_CONTENT")
    NANA_CONTENT,
    @SerializedName("EXPERIENCE")
    EXPERIENCE,
    @SerializedName("FESTIVAL")
    FESTIVAL,
    @SerializedName("NATURE")
    NATURE,
    @SerializedName("MARKET")
    MARKET,
    @SerializedName("RESTAURANT")
    RESTAURANT,
}