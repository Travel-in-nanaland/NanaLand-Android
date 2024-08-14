package com.jeju.nanaland.domain.usecase.report

import com.jeju.nanaland.domain.repository.ReportRepository
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.domain.request.report.InformationModificationProposalRequest
import kotlinx.coroutines.flow.flow
import java.io.File

class InformationModificationProposalUseCase(
    private val repository: ReportRepository
) {
    operator fun invoke(
        data: InformationModificationProposalRequest,
        images: List<UriRequestBody>
    ) = flow {
        val response = repository.informationModificationProposal(data, images)
        emit(response)
    }
}