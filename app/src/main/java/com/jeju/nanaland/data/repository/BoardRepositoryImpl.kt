package com.jeju.nanaland.data.repository

import androidx.paging.PagingSource
import com.jeju.nanaland.data.api.BoardApi
import com.jeju.nanaland.domain.entity.notice.NoticeDetail
import com.jeju.nanaland.domain.entity.notice.NoticeSummery
import com.jeju.nanaland.domain.repository.BoardRepository
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class BoardRepositoryImpl(
    private val api: BoardApi
): BoardRepository, NetworkResultHandler {

    override suspend fun getNotice(id: Int): NetworkResult<NoticeDetail> {
        return handleResult {
            api.getNotice(id)
        }
    }

    override fun getNoticeList(): PagingSource<Int, NoticeSummery> {
        return handleResultPaging { page, size ->
            api.getNoticeList(
                page = page,
                size = size,
            )
        }
    }
}