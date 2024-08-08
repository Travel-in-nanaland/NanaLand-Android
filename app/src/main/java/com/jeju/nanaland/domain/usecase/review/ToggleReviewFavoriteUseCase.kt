package com.jeju.nanaland.domain.usecase.review

import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.review.ToggleReviewFavoriteRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToggleReviewFavoriteUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    operator fun invoke(
        data: ToggleReviewFavoriteRequest
    ) = flow {
        val response = repository.toggleReviewFavorite(data)
        emit(response)
    }
}