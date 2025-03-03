package com.jeju.nanaland.domain.usecase.review

import com.jeju.nanaland.domain.repository.ReviewRepository
import javax.inject.Inject

class GetKrAddressUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(
        id: Int,
        category: String,
        number: Int? = null,
    ) = repository.getKrAddress(id, category, number)
}