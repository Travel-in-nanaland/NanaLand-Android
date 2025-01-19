package com.jeju.nanaland.data.repository

import com.jeju.nanaland.data.api.ReportApi
import com.jeju.nanaland.domain.entity.report.ReportDetail
import com.jeju.nanaland.domain.repository.ReportRepository
import com.jeju.nanaland.domain.request.report.InformationModificationProposalRequest
import com.jeju.nanaland.util.network.NetworkResult
import com.jeju.nanaland.util.network.NetworkResultHandler

class ReportRepositoryImpl(
    private val reportApi: ReportApi
) : ReportRepository, NetworkResultHandler {

    override suspend fun informationModificationProposal(
        data: InformationModificationProposalRequest,
    ): NetworkResult<String?> {
        return handleResult { reportApi.informationModificationProposal(data)}
    }

    override suspend fun reportReview(
        data: ReportDetail,
    ): NetworkResult<String?> {
        return handleResult { reportApi.reportReview(data) }
    }
}