package com.jeju.nanaland.domain.usecase.review

import com.jeju.nanaland.domain.repository.ReviewRepository
import com.jeju.nanaland.domain.request.search.GetAllSearchResultListRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetReviewListUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    operator fun invoke(
        data: GetAllSearchResultListRequest
    ) = flow {
        emit(Unit)
    }
}