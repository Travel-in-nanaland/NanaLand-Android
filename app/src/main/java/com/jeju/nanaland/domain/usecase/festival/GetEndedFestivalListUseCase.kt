package com.jeju.nanaland.domain.usecase.festival

import com.jeju.nanaland.domain.repository.FestivalRepository
import com.jeju.nanaland.domain.request.festival.GetEndedFestivalListRequest
import kotlinx.coroutines.flow.flow

class GetEndedFestivalListUseCase(
    private val repository: FestivalRepository
) {
    operator fun invoke(
        data: GetEndedFestivalListRequest
    ) = flow {
        val response = repository.getEndedFestivalList(data)
        emit(response)
    }
}