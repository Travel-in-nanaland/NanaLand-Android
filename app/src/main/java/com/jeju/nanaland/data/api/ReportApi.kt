package com.jeju.nanaland.data.api

import com.jeju.nanaland.domain.entity.report.ReportDetail
import com.jeju.nanaland.domain.request.report.InformationModificationProposalRequest
import com.jeju.nanaland.domain.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ReportApi {

    // 정보 수정 제안
    @POST("report/info-fix")
    suspend fun informationModificationProposal(
        @Body data: InformationModificationProposalRequest,
    ): Response<ResponseWrapper<String>>

    // 리뷰 신고
    @POST("report/claim")
    suspend fun reportReview(
        @Body data: ReportDetail,
    ): Response<ResponseWrapper<String>>
}