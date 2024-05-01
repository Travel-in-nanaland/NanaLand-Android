package com.nanaland.domain.usecase.nanapick

import com.nanaland.domain.repository.NanaPickRepository
import kotlinx.coroutines.flow.flow

class GetHomePreviewBannerUseCase(
    private val repository: NanaPickRepository
) {
    operator fun invoke() = flow {
        val response = repository.getHomePreviewBanner()
        emit(response)
    }
}