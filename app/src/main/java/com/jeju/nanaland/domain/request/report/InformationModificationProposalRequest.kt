package com.jeju.nanaland.domain.request.report

import com.google.gson.annotations.SerializedName

data class InformationModificationProposalRequest(
    @SerializedName("postId")
    val postId: Long,
    @SerializedName("fixType")
    val fixType: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("email")
    val email: String
)
