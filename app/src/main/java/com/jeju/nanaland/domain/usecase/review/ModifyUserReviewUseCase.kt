package com.jeju.nanaland.domain.usecase.review

import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.review.CreateReviewRequest
import javax.inject.Inject

class ModifyUserReviewUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        id: Int,
        newImages: List<UriRequestBody>?,
        data: CreateReviewRequest
    ) = repository.modifyUserReview(id,newImages,data)
}