package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.report.InformationModificationProposalRequest
import com.jeju.nanaland.globalvalue.type.ReportType
import com.jeju.nanaland.util.network.NetworkResult
import java.io.File

interface ReportRepository {

    // 정보 수정 제안
    suspend fun informationModificationProposal(
        data: InformationModificationProposalRequest,
        image: File?
    ): NetworkResult<String?>

    // 리뷰 신고
    suspend fun reportReview(
        reviewId: Int,
        claimType: ReportType,
        content: String,
        images: List<UriRequestBody>
    ): NetworkResult<String?>
}