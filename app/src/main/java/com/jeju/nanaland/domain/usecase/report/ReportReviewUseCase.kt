package com.jeju.nanaland.domain.usecase.report

import com.jeju.nanaland.domain.entity.report.ReportDetail
import com.jeju.nanaland.domain.repository.ReportRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReportReviewUseCase @Inject constructor (
    private val repository: ReportRepository
) {
    operator fun invoke(
        data: ReportDetail,
    ) = flow {
        val response = repository.reportReview(data)
        emit(response)
    }
}