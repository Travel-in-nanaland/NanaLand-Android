package com.nanaland.domain.usecase.nature

import com.nanaland.domain.repository.NatureRepository
import com.nanaland.domain.request.nature.GetNatureListRequest
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