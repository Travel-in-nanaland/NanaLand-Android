package com.jeju.nanaland.domain.usecase.review

import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.review.GetMyReviewListRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMyReviewDetailUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    operator fun invoke(
        reviewId: Int
    ) = flow {
        val response = repository.getMyReviewList(GetMyReviewListRequest(reviewId))
        emit(response)
    }

}