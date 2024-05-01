package com.nanaland.domain.request.member

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("locale")
    val locale: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("birthDate")
    val birthDate: String,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("providerId")
    val providerId: String
)
