package com.jeju.nanaland.domain.usecase.nature

import com.jeju.nanaland.domain.repository.NatureRepository
import com.jeju.nanaland.domain.request.nature.GetNatureListRequest
import kotlinx.coroutines.flow.flow

class GetNatureListUseCase(
    private val repository: NatureRepository
) {
    operator fun invoke(
        data: GetNatureListRequest
    ) = flow {
        val response = repository.getNatureList(data)
        emit(response)
    }
}