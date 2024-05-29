package com.jeju.nanaland.domain.request.member

import com.google.gson.annotations.SerializedName

data class UpdatePolicyAgreementRequest(
    @SerializedName("consentType")
    val consentType: String,
    @SerializedName("consent")
    val consent: Boolean
)
