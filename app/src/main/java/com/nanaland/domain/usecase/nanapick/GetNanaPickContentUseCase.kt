package com.nanaland.domain.usecase.nanapick

import com.nanaland.domain.repository.NanaPickRepository
import com.nanaland.domain.request.nanapick.GetNanaPickContentRequest
import kotlinx.coroutines.flow.flow

class GetNanaPickContentUseCase(
    private val repository: NanaPickRepository
) {
    operator fun invoke(
        data: GetNanaPickContentRequest
    ) = flow {
        val response = repository.getNanaPickContent(data)
        emit(response)
    }
}