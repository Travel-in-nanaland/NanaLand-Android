package com.jeju.nanaland.domain.usecase.review

import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.review.CreateReviewRequest
import javax.inject.Inject

class ModifyUserReviewUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        id: Int,
        data: CreateReviewRequest
    ) = repository.modifyUserReview(id,data)
}