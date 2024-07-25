package com.jeju.nanaland.domain.repository

import com.jeju.nanaland.domain.request.report.InformationModificationProposalRequest
import com.jeju.nanaland.util.network.NetworkResult
import java.io.File

interface ReportRepository {

    // 정보 수정 제안
    suspend fun informationModificationProposal(
        data: InformationModificationProposalRequest,
        image: File?
    ): NetworkResult<String?>
}