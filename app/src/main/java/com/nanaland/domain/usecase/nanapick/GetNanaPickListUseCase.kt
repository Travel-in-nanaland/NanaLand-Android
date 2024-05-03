package com.nanaland.domain.usecase.nanapick

import com.nanaland.domain.repository.NanaPickRepository
import com.nanaland.domain.request.nanapick.GetNanaPickListRequest
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