package com.jeju.nanaland.domain.usecase.nanapick

import com.jeju.nanaland.domain.repository.NanaPickRepository
import com.jeju.nanaland.domain.request.nanapick.GetNanaPickContentRequest
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