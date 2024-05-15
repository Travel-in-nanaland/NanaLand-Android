package com.jeju.nanaland.domain.usecase.festival

import com.jeju.nanaland.domain.repository.FestivalRepository
import com.jeju.nanaland.domain.request.festival.GetMonthlyFestivalListRequest
import kotlinx.coroutines.flow.flow

class GetMonthlyFestivalListUseCase(
    private val repository: FestivalRepository
) {
    operator fun invoke(
        data: GetMonthlyFestivalListRequest
    ) = flow {
        val response = repository.getMonthlyFestivalList(data)
        emit(response)
    }
}