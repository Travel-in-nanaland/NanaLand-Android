package com.jeju.nanaland.domain.usecase.review

import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.review.DeleteReviewRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteReviewUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    operator fun invoke(
        data: DeleteReviewRequest
    ) = flow {
        val response = repository.deleteReview(data)
        emit(response)
    }
}