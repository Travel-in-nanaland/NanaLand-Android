package com.jeju.nanaland.domain.entity.member

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.common.ImageUrl

data class UserProfile(
    @SerializedName("consentItems")
    val consentItem: List<ConsentItem>,
    @SerializedName("email")
    val email: String,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("profileImage")
    val profileImage: ImageUrl,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("level")
    val level: Int,
    @SerializedName("travelType")
    val travelType: String,
    @SerializedName("hashtags")
    val hashTags: List<String>
)
