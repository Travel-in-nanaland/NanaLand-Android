package com.nanaland.domain.usecase.festival

import com.nanaland.domain.repository.FestivalRepository
import com.nanaland.domain.request.festival.GetEndedFestivalListRequest
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