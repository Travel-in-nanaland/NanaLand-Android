package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.notice.NoticeDetail
import com.jeju.nanaland.domain.entity.notice.NoticeSummery
import com.jeju.nanaland.domain.entity.notification.Notification
import com.jeju.nanaland.domain.response.ResponsePagingWrapper
import com.jeju.nanaland.domain.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BoardApi {

    // 공지사항 상세 조회
    @GET("notice/{id}")
    suspend fun getNotice(
        @Path("id") id: Int
    ): Response<ResponseWrapper<NoticeDetail>>

    // 공지사항 리스트 조회
    @GET("notice/list")
    suspend fun getNoticeList(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<ResponseWrapper<ResponsePagingWrapper<NoticeSummery>>>

    // 사용자 알림 리스트 조회
    @GET("notification/list")
    suspend fun getNotificationList(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<ResponseWrapper<ResponsePagingWrapper<Notification>>>

}