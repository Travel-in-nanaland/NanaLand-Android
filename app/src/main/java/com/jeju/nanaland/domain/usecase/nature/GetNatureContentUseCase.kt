package com.jeju.nanaland.domain.usecase.nature

import com.jeju.nanaland.domain.repository.NatureRepository
import com.jeju.nanaland.domain.request.nature.GetNatureContentRequest
import kotlinx.coroutines.flow.flow

class GetNatureContentUseCase(
    private val repository: NatureRepository
) {
    operator fun invoke(
        data: GetNatureContentRequest
    ) = flow {
        val response = repository.getNatureContent(data)
        emit(response)
    }
}