package com.nanaland.domain.usecase.nature

import com.nanaland.domain.repository.NatureRepository
import com.nanaland.domain.request.nature.GetNatureContentRequest
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