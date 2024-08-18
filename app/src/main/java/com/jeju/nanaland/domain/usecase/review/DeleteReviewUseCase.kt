package com.jeju.nanaland.domain.usecase.review

import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.review.DeleteReviewRequest
import javax.inject.Inject

class DeleteReviewUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        data: DeleteReviewRequest
    ) = repository.deleteReview(data)
}