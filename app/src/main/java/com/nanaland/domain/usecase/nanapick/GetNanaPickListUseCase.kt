package com.nanaland.domain.usecase.nanapick

import com.nanaland.util.log.LogUtil
import com.nanaland.util.network.onError
import com.nanaland.util.network.onSuccess
import com.nanaland.domain.entity.nanapick.NanaPickThumbnail
import com.nanaland.domain.repository.NanaPickRepository
import com.nanaland.domain.request.nanapick.GetNanaPickListRequest
import com.nanaland.util.ui.UiState
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