package com.jeju.nanaland.domain.usecase.review

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.jeju.nanaland.domain.repository.ReviewRepository
import javax.inject.Inject

class GetReviewListByUserUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    operator fun invoke(
        userId: Int?
    ) = Pager(
        config = PagingConfig(pageSize = 4),
    ) {
        repository.getReviewListByUser(userId)
    }
}