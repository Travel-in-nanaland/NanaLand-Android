package com.jeju.nanaland.domain.usecase.festival

import com.jeju.nanaland.domain.repository.FestivalRepository
import com.jeju.nanaland.domain.request.festival.GetSeasonalFestivalListRequest
import kotlinx.coroutines.flow.flow

class GetSeasonalFestivalListUseCase(
    private val repository: FestivalRepository
) {
    operator fun invoke(
        data: GetSeasonalFestivalListRequest
    ) = flow {
        val response = repository.getSeasonalFestivalList(data)
        emit(response)
    }
}