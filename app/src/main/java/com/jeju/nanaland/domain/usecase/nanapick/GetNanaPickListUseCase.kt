package com.jeju.nanaland.domain.usecase.nanapick

import com.jeju.nanaland.domain.repository.NanaPickRepository
import com.jeju.nanaland.domain.request.nanapick.GetNanaPickListRequest
import kotlinx.coroutines.flow.flow

class GetNanaPickListUseCase(
    private val repository: NanaPickRepository
) {
    operator fun invoke(
        data: GetNanaPickListRequest
    ) = flow {
        val response = repository.getNanaPickList(data)
        emit(response)
    }
}