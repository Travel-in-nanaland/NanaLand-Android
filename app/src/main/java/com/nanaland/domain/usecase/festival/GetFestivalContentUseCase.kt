package com.nanaland.domain.usecase.festival

import com.nanaland.domain.repository.FestivalRepository
import com.nanaland.domain.request.festival.GetFestivalContentRequest
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