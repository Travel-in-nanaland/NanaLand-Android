package com.jeju.nanaland.domain.request.review

import com.google.gson.annotations.SerializedName

data class GetReviewAutoCompleteKeywordRequest(
    @SerializedName("keyword")
    val keyword: String
)
