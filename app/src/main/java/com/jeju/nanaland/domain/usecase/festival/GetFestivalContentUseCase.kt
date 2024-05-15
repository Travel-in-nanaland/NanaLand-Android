package com.jeju.nanaland.domain.usecase.festival

import com.jeju.nanaland.domain.repository.FestivalRepository
import com.jeju.nanaland.domain.request.festival.GetFestivalContentRequest
import kotlinx.coroutines.flow.flow

class GetFestivalContentUseCase(
    private val repository: FestivalRepository
) {
    operator fun invoke(
        data: GetFestivalContentRequest
    ) = flow {
        val response = repository.getFestivalContent(data)
        emit(response)
    }
}