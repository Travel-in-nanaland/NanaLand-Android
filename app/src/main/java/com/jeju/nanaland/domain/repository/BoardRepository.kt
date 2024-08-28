package com.jeju.nanaland.domain.repository

import androidx.paging.PagingSource
import com.jeju.nanaland.domain.entity.notice.NoticeDetail
import com.jeju.nanaland.domain.entity.notice.NoticeSummery
import com.jeju.nanaland.domain.entity.notification.Notification
import com.jeju.nanaland.util.network.NetworkResult

interface BoardRepository {

    // 공지사항 상세 조회
    suspend fun getNotice(
        id: Int
    ): NetworkResult<NoticeDetail>

    // 공지사항 리스트 조회
    fun getNoticeList(): PagingSource<Int, NoticeSummery>

    // 사용자 알림 리스트 조회
    fun getNotificationList(): PagingSource<Int, Notification>
}