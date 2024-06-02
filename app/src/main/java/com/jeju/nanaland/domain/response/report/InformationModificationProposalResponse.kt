package com.jeju.nanaland.domain.response.report

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.domain.entity.nature.NatureContentData

data class InformationModificationProposalResponse(
    @SerializedName("status")
    val status: Long,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: String?
)
