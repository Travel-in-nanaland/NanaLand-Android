package com.jeju.nanaland.domain.usecase.board

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.jeju.nanaland.domain.repository.BoardRepository
import javax.inject.Inject

class GetNotificationListUseCase @Inject constructor(
    private val repository: BoardRepository
) {
    private var currentPagingSource: PagingSource<*, *>? = null

    operator fun invoke() = Pager(
        config = PagingConfig(pageSize = 8),
    ) {
        val newPagingSource = repository.getNotificationList()
        currentPagingSource = newPagingSource
        newPagingSource
    }
    fun invalidate() {
        currentPagingSource?.invalidate()
    }
}