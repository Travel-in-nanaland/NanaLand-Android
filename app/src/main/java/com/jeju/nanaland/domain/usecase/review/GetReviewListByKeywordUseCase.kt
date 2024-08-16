package com.jeju.nanaland.domain.usecase.review

import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.review.GetReviewAutoCompleteKeywordRequest
import javax.inject.Inject

class GetReviewListByKeywordUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        data: String
    ) = repository.getReviewAutoCompleteKeyword(GetReviewAutoCompleteKeywordRequest(data))
}