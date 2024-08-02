package com.jeju.nanaland.domain.usecase.review

import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.review.GetReviewListByPostRequest
import com.jeju.nanaland.domain.request.search.GetAllSearchResultListRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetReviewListByPostUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    operator fun invoke(
        data: GetReviewListByPostRequest
    ) = flow {
        val response = repository.getReviewListByPost(data)
        emit(response)
    }
}