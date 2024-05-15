package com.jeju.nanaland.domain.usecase.favorite

import com.jeju.nanaland.domain.repository.FavoriteRepository
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import kotlinx.coroutines.flow.flow

class ToggleFavoriteUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(
        data: ToggleFavoriteRequest
    ) = flow {
        val response = repository.toggleFavorite(data)
        emit(response)
    }
}