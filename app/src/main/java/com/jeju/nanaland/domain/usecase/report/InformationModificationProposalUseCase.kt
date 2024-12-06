package com.jeju.nanaland.domain.usecase.report

import com.jeju.nanaland.domain.repository.ReportRepository
import com.jeju.nanaland.domain.request.report.InformationModificationProposalRequest
import kotlinx.coroutines.flow.flow

class InformationModificationProposalUseCase(
    private val repository: ReportRepository
) {
    operator fun invoke(
        data: InformationModificationProposalRequest,
    ) = flow {
        val response = repository.informationModificationProposal(data)
        emit(response)
    }
}