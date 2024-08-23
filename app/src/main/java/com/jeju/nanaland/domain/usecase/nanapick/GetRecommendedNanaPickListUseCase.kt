package com.jeju.nanaland.domain.usecase.nanapick

import com.jeju.nanaland.domain.repository.NanaPickRepository
import kotlinx.coroutines.flow.flow

class GetRecommendedNanaPickListUseCase(
    private val repository: NanaPickRepository
) {
    operator fun invoke() = flow {
        val response = repository.getRecommendedNanaPickList()
        emit(response)
    }
}