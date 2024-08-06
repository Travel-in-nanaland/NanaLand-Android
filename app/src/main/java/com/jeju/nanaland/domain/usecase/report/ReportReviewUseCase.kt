package com.jeju.nanaland.domain.usecase.report

import com.jeju.nanaland.domain.repository.ReportRepository
import com.jeju.nanaland.domain.request.UriRequestBody
import com.jeju.nanaland.globalvalue.type.ReportType
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReportReviewUseCase @Inject constructor (
    private val repository: ReportRepository
) {
    operator fun invoke(
        reviewId: Int,
        email: String,
        claimType: ReportType,
        content: String,
        image: List<UriRequestBody>
    ) = flow {
        val response = repository.reportReview(reviewId, email, claimType,  content, image)
        emit(response)
    }
}