package com.jeju.nanaland.domain.usecase.review

import com.jeju.nanaland.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToggleReviewFavoriteUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    operator fun invoke(
        id: Int
    ) = flow {
        val response = repository.toggleReviewFavorite(id)
        emit(response)
    }
}