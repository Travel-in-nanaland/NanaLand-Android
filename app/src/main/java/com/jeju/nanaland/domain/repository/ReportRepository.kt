package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.entity.report.ReportDetail
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.report.InformationModificationProposalRequest
import com.jeju.nanaland.util.network.NetworkResult

interface ReportRepository {

    // 정보 수정 제안
    suspend fun informationModificationProposal(
        data: InformationModificationProposalRequest,
    ): NetworkResult<String?>

    // 리뷰 신고
    suspend fun reportReview(
        data: ReportDetail,
    ): NetworkResult<String?>
}