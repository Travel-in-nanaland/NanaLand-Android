package com.jeju.nanaland.domain.usecase.review

import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.review.CreateReviewRequest
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import javax.inject.Inject

class CreateReviewUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        id: Int,
        category: ReviewCategoryType,
        images: List<UriRequestBody>,
        data: CreateReviewRequest
    ) = repository.createReview(id,category,images,data)
}