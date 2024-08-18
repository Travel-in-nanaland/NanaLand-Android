package com.jeju.nanaland.domain.usecase.review

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.jeju.nanaland.domain.repository.ReviewRepository
import javax.inject.Inject

class GetReviewListByUserUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    private var currentPagingSource: PagingSource<*, *>? = null

    operator fun invoke(
        userId: Int?
    ) = Pager(
        config = PagingConfig(pageSize = 4),
    ) {
        val newPagingSource = repository.getReviewListByUser(userId)
        currentPagingSource = newPagingSource
        newPagingSource
    }
    fun invalidate() {
        currentPagingSource?.invalidate()
    }
}