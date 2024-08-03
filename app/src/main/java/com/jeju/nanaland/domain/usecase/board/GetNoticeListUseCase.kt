package com.jeju.nanaland.domain.usecase.board

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.jeju.nanaland.domain.repository.BoardRepository
import javax.inject.Inject

class GetNoticeListUseCase @Inject constructor(
    private val repository: BoardRepository
) {
    operator fun invoke() = Pager(
        config = PagingConfig(pageSize = 4),
    ) {
        repository.getNoticeList()
    }
}